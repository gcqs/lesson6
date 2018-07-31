package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.SubjectService;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView add(Subject subject){

        IdWorker worker2 = new IdWorker(2);
        subject.setSubjectId(worker2.nextId());
        subject.setAvgNum(0);
        subject.setStudentId(Long.valueOf(0));
        subjectService.add(subject);
        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }

    @RequestMapping("/update")
    public ModelAndView update(Subject subject){

        subjectService.update(subject);
        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }

    @RequestMapping("/delete")
    public ModelAndView delete(Subject subject){

        subjectService.delete(subject.getSubjectId());
        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }

}
