package com.biz.lesson.web.controller.manage;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.biz.lesson.business.user.GradeService;
import com.biz.lesson.business.user.StudentManager;
import com.biz.lesson.business.user.SubjectService;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import com.biz.lesson.util.upFileUti;
import com.biz.lesson.vo.studentVo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 */

@Controller
@RequestMapping(value = "manage/student")
public class StudentController {

    @Autowired
    @Qualifier("studentManager")
    private StudentManager studentManager;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    private static String endPoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIXzZy6YmykJwu";
    private static String accessKeySecret = "7bGEWfvAHutN4zdHIOFgWPFlcii6xK";
    private static String bucketName = "zcram";
    private static String objectName = "key/";


    //显示所有信息
    @RequestMapping("/all")
    public ModelAndView all(Integer page) throws  Exception{

        Integer number = page;
        if(number==null||number<1){
            number=1;
        }
        ModelAndView view = new ModelAndView("manage/ace/tables");
        Page<Student> all = studentManager.findAll(number);
        if(all.getContent()!=null){

            view.addObject("studentAll",all);
            view.addObject("pagename","all");
//            System.out.println(all.getContent().get(0).getAvgNum()+"+++++++++++++avg++++++++++++++");
//            System.out.println(all.getNumber()+"number+++++++++");
//            System.out.println(all.getTotalPages()+"total+++++++++");

        }


        return view;
    }

    //进入添加页面
    @RequestMapping("/add")
    public ModelAndView add() throws  Exception{

        Page<Grade> all = gradeService.findAll(1, 1000);

        ModelAndView view = new ModelAndView("manage/ace/add");
//        System.out.println("============"+all.getContent().get(0));
        view.addObject("grade",all.getContent());
        return view;

    }

    //更新
    @RequestMapping("/update")
    public ModelAndView update(Student student,HttpServletResponse response) throws  Exception{

        StudentVo student1 = studentManager.findStudent(student.getStudentId());

        Page<Grade> all = gradeService.findAll(1, 1000);
        ModelAndView view = new ModelAndView("manage/ace/update");
        view.addObject("student",student1);
        view.addObject("grade",all.getContent());
        return view;



    }

    @RequestMapping("/save_update")
    public void save_update(Student student,HttpServletResponse response) throws  Exception{

        studentManager.update(student);

        response.sendRedirect("/manage/student/all.do");
    }

    //删除
    @RequestMapping("/delete")
    public void delete(Student student,HttpServletResponse response) throws  Exception{


//        System.out.println("删除了。。。。。。。。。。。。。"+student.getStudentId());

        studentManager.delete(student);

        response.sendRedirect("/manage/student/all.do");

    }

    //查找
    @RequestMapping("/find")
    public ModelAndView find(Integer page,Student student,String start,String end, HttpServletRequest request) throws  Exception{
        Integer number = page;
        if(number==null||number<1){
            number=1;
        }

//        System.out.println(start+"====="+end);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = format.parse(start);
        Date date2 = format.parse(end);
//        System.out.println(date1.getTime()+"++++++++++++++++++++++++++++++++=====");

        //按姓名，学号，日期进行查询
        Page<Student> all = studentManager.findByDate(number, student, date1, date2);

        ModelAndView view = new ModelAndView("manage/ace/tables");

        view.addObject("studentAll",all);

        view.addObject("pagename","find");
        return view;

    }


    //保存添加
    @RequestMapping("/save_add")
    public void save_add(Student student,HttpServletRequest request,String birthday,String grade,String number,HttpServletResponse response,MultipartFile file) throws  Exception{

       // System.out.println(birthday+"==========================");

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);

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



        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/images/");
            //上传文件名
            String filename = file.getOriginalFilename();

            String str =filename.substring(filename.lastIndexOf("."),filename.length());

            String newname = UUID.randomUUID().toString()+str;

            File filepath = new File(path, newname);

            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + newname));

            InputStream is = new FileInputStream(filepath);

            ossClient.putObject(bucketName, newname, is);

            String url = "http://zcram.oss-cn-beijing.aliyuncs.com/"+newname;
            student.setImg(url);

            //关闭连接
            ossClient.shutdown();
            is.close();


        }


        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(birthday);

       // String filename ="112.png";
        IdWorker worker2 = new IdWorker(2);
        student.setStudentId(worker2.nextId());
        student.setGradeId(Long.valueOf(grade));
        student.setAvgNum(0);
        student.setSubjectNum(0);
        student.setNum(Integer.valueOf(number));

        student.setDate(date);
        studentManager.add(student);


        response.sendRedirect("/manage/student/all.do");

    }

    //录入
    @RequestMapping("/addAvg")
    public ModelAndView addAvg(String studentId) throws Exception{


        List<Subject> list = studentManager.findSubject(Long.valueOf(studentId));
        ModelAndView view = new ModelAndView("manage/ace/addAvg");
        if(list!=null||!list.isEmpty()){
            view.addObject("subject",list);

        }

        return view;

    }

    @RequestMapping("/save_addAvg")
    public void save_addAvg(String [] name,String [] avgNum,String studentId,HttpServletResponse response) throws Exception{

        int avg=0;
        int i =0;
        Student student = new Student();
        for(i=0;i<name.length;i++){
            Subject subject = new Subject();
            subject.setStudentId(Long.valueOf(studentId));
            subject.setName(name[i]);
            subject.setAvgNum(Integer.valueOf(avgNum[i]));

            studentManager.addAvg(subject);
            student.setStudentId(subject.getStudentId());
            avg= avg+ subject.getAvgNum();
        }
        student.setAvgNum(avg/i);

        studentManager.updateAvg(student);


        response.sendRedirect("/manage/student/all.do");
    }

    //选课
    @RequestMapping("/addSubject")
    public ModelAndView addSubject(String studentId){

        List<Subject> subject = studentManager.findSubject(Long.valueOf(studentId));

        List<Subject> noSubject = studentManager.findNoSubject(studentId);

        ModelAndView view = new ModelAndView("manage/ace/addSubject");

        view.addObject("noSubject",noSubject);
        view.addObject("subject",subject);
        view.addObject("studentId",studentId);
        return view;
    }

    @RequestMapping("/save_addSubject")
    public void save_addSubject(String[] name,String studentId, HttpServletResponse response) throws Exception {


        System.out.println("++++++++++++++++++++");
        IdWorker worker2 = new IdWorker(2);
        for (String s:name){
            Subject subject = new Subject();
            subject.setAvgNum(0);
            subject.setSubjectId(worker2.nextId());
            subject.setStudentId(Long.valueOf(studentId));
            subject.setName(s);

            studentManager.addSubject(subject);

        }


        response.sendRedirect("/manage/student/all.do");
    }
}
