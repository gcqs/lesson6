package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.GradeService;
import com.biz.lesson.business.user.StudentManager;
import com.biz.lesson.business.user.SubjectService;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import com.biz.lesson.util.upFileUti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
//            System.out.println(all.getContent().get(0).getName());

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
    public ModelAndView update(Student student) throws  Exception{

        studentManager.update(student);
        ModelAndView view = new ModelAndView("manage/ace/tables");
        return view;

    }

    //删除
    @RequestMapping("/delete")
    public ModelAndView delete(Student student) throws  Exception{


        System.out.println("删除了。。。。。。。。。。。。。"+student.getStudentId());

        studentManager.delete(student);

        ModelAndView view = new ModelAndView("manage/ace/tables");
        return view;

    }

    //查找
    @RequestMapping("/find")
    public ModelAndView find(Page page,Student student, HttpServletRequest request) throws  Exception{
        Integer number = page.getNumber();
        if(number==null||number<1){
            number=1;
        }

        //按姓名，id，日期进行查询
//        studentManager.findByDate(number,student,date1,date2);

        ModelAndView view = new ModelAndView("manage/ace/tables");
        return view;

    }


    //保存添加
    @RequestMapping("/save_add")
    public void save_add(Student student,HttpServletRequest request,String birthday,String grade,String number) throws  Exception{

//        System.out.println(birthday+"==========================");

//        InputStream inputStream1 = request.getInputStream();
//        String filename = upFileUti.upFile(inputStream1, request);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(birthday);

        String filename ="112.png";
        String url = "zcram.oss-cn-beijing.aliyuncs.com/"+filename;
        IdWorker worker2 = new IdWorker(2);
        student.setStudentId(worker2.nextId());
        student.setGradeId(Long.valueOf(grade));
        student.setAvgNum(0);
        student.setSubjectNum(0);
        student.setNum(Integer.valueOf(number));
        student.setImg(url);
        student.setDate(date);
        studentManager.add(student);


        all(1);

    }

    //录入
    @RequestMapping("/addAvg")
    public ModelAndView addAvg(String studentId) throws Exception{


        List<Subject> list = studentManager.findSubject(Long.valueOf(studentId));
        ModelAndView view = new ModelAndView("manage/ace/addAvg");
        view.addObject("subject",list);
        return view;
    }

    @RequestMapping("/save_addAvg")
    public ModelAndView save_addAvg(List<Subject> list) throws Exception{

        int avg=0;
        int i =0;
        Student student = new Student();
        for(Subject subject : list){
            studentManager.addAvg(subject);
            student.setStudentId(subject.getStudentId());
            avg= avg+ subject.getAvg();
            i++;
        }
        student.setAvgNum(avg/i);

        studentManager.updateAvg(student);

        ModelAndView view = new ModelAndView("manage/ace/tables");
        return view;
    }

    //选课
    @RequestMapping("/addSubject")
    public ModelAndView addSubject(String studentId){

        List<Subject> subject = studentManager.findSubject(Long.valueOf(studentId));

        List<Subject> noSubject = studentManager.findNoSubject(studentId);

        ModelAndView view = new ModelAndView("manage/ace/addSubject");

        view.addObject("noSubject",noSubject);
        view.addObject("subject",subject);
        return view;
    }

    @RequestMapping("/save_addSubject")
    public void sava_addSubject(List<String> list,String studentId, HttpServletResponse response) throws Exception {


        System.out.println("++++++++++++++++++++");
        IdWorker worker2 = new IdWorker(2);
        for (String s:list){
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
