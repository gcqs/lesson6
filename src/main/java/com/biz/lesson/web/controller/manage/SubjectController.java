package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.SubjectService;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学科
 */
@Controller
@RequestMapping(value = "manage/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/all")
    public ModelAndView all(){

        List<Subject> all = subjectService.findAll();
        /*
        all:student属性： name count avg
         */
        ModelAndView view = new ModelAndView("manage/ace/subjectTables");
        view.addObject("subjectAll",all);
        return view;

    }

    @RequestMapping("/add")
    public void add(Subject subject,HttpServletResponse response)throws  Exception{

        IdWorker worker2 = new IdWorker(2);
        subject.setSubjectId(worker2.nextId());
        subject.setAvgNum(0);
        subject.setStudentId(Long.valueOf(0));
        subjectService.add(subject);
        response.sendRedirect("/manage/subject/all.do");


    }

    @RequestMapping("/update")
    public ModelAndView update(Subject subject){


        ModelAndView view = new ModelAndView("manage/ace/subjectUpdate");

        view.addObject("id",subject.getSubjectId());
        return view;

    }

    @RequestMapping("/save_update")
    public void save_update(Subject subject, HttpServletResponse response) throws  Exception{

        subjectService.update(subject);
        response.sendRedirect("/manage/subject/all.do");

    }

    @RequestMapping("/delete")
    public void delete(Subject subject,HttpServletResponse response) throws  Exception{

        subjectService.delete(subject.getSubjectId());


        response.sendRedirect("/manage/subject/all.do");

    }

}
