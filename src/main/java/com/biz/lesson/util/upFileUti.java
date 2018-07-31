package com.biz.lesson.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ObjectMetadata;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class upFileUti {
    private static final long serialVersionUID = 1L;
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIXzZy6YmykJwu";
    private static String accessKeySecret = "7bGEWfvAHutN4zdHIOFgWPFlcii6xK";
    private static String bucketName = "zcram";
    private static String objectName = "key/";


    public static String upFile(InputStream inputStream,HttpServletRequest request) throws Exception {


        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if (!ossClient.doesBucketExist(bucketName)) {
            /*
             * Create a new OSS bucket
             */
            //System.out.println("Creating bucket " + bucketName + "\n");
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }

        //tempFile指向临时文件
        File tempFile = new File(request.getServletContext().getRealPath("/temp"));
        //outputStram文件输出流指向这个临时文件
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        byte b[] = new byte[1024];
        int n;
        while(( n = inputStream.read(b)) != -1){
            outputStream.write(b, 0, n);
        }
        //关闭输出流、输入流
        outputStream.close();
        inputStream.close();


        RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
//		l = new String(l.getBytes("8859_1"),"gbk");
        String str2 = randomFile.readLine();
        //编码转换
        str2 = new String(str2.getBytes("8859_1"),"utf-8");
        //System.out.println(str2);
        String str = randomFile.readLine();
        str = new String(str.getBytes("8859_1"),"utf-8");
        System.out.println("str==="+str);
        int beginIndex = str.lastIndexOf("=") + 2;
        int endIndex = str.lastIndexOf("\"");
        String filename = str.substring(beginIndex, endIndex);
        System.out.println("filename:" + filename);

        //重新定位文件指针到文件头
        randomFile.seek(0);
        long startPosition = 0;
        int i = 1;
        //获取文件内容 开始位置
        while(( n = randomFile.readByte()) != -1 && i <=4){
            if(n == '\n'){
                startPosition = randomFile.getFilePointer();
                i ++;
            }
        }
        startPosition = randomFile.getFilePointer() -1;
        //获取文件内容 结束位置
        randomFile.seek(randomFile.length());
        long endPosition = randomFile.getFilePointer();
        int j = 1;
        while(endPosition >=0 && j<=2){
            endPosition--;
            randomFile.seek(endPosition);
            if(randomFile.readByte() == '\n'){
                j++;
            }
        }
        endPosition = endPosition -1;


//        String realPath = "E:\\temp";
//        File fileupload = new File(realPath);
        //System.out.println(realPath);
//        if(!fileupload.exists()){
//            fileupload.mkdir();
//        }
//        File saveFile = new File(realPath,filename);
//        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
//        //从临时文件当中读取文件内容（根据起止位置获取）
//        randomFile.seek(startPosition);
//        while(startPosition < endPosition){
//            randomAccessFile.write(randomFile.readByte());
//            startPosition = randomFile.getFilePointer();
//        }
//
        InputStream is = new FileInputStream(tempFile);
        String fileName = tempFile.getName();
        System.out.println(fileName);
        long fileLength = tempFile.length();
        System.out.println(fileLength);
        //创建上传Object的Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(is.available());
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(filename.substring(filename.lastIndexOf(".")));
        metadata.setContentDisposition("filename/filesize=" + filename + "/" + fileLength + "Byte.");
        // 上传文件
        ossClient.putObject(bucketName, filename, is, metadata);
        is.close();
        ossClient.shutdown();

        //关闭输入输出流、删除临时文件
       // randomAccessFile.close();
        randomFile.close();
        tempFile.delete();

        return filename;

    }
}
