package com.biz.lesson.test.dao.admin;

import com.biz.lesson.dao.studnet.StudentRepository;
import com.biz.lesson.model.student.Student;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class Test {

    @Autowired
    private StudentRepository studentDao;


    @org.junit.Test
    public void test1(){
        Student student = new Student();
        student.setAvgNum(0);
        student.setDate(new Date());
        student.setGradeId(Long.valueOf(1));
        student.setName("张春11");
        student.setNum(110);
        student.setSex("1");
        student.setSubjectNum(0);
        student.setImg("22");
        student.setStudentId(Long.valueOf(1));

        studentDao.save(student);


        Student student1= studentDao.findByStudentId(Long.valueOf(1));

        System.out.println(student1.getName());
        System.out.println("========================xxxx");

    }
}
