package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.GradeService;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 班级
 */

@Controller
@RequestMapping(value = "manage/grade")
public class GradeController {


    @Autowired
    private GradeService gradeService;


    @RequestMapping("/all")
    public ModelAndView all(Integer page){

        Integer number = page;
        if(number==null||number<1){
            number=1;
        }

        ModelAndView view = new ModelAndView("manage/ace/gradeTables");

        Page<Grade> all = gradeService.findAll(number,10);

        view.addObject("gradeAll",all);

        return view;

    }

    @RequestMapping("/add")
    public ModelAndView add(Grade grade){

        System.out.println(grade.getName()+"=====================================");
        IdWorker worker2 = new IdWorker(2);
        grade.setGradeId(worker2.nextId());
        grade.setAvgNum(0);
        grade.setNum(0);
        gradeService.add(grade);

        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }

    @RequestMapping("/update")
    public ModelAndView update(Grade grade){

        gradeService.add(grade);

        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }
    @RequestMapping("/delete")
    public ModelAndView delete(Grade grade){

       gradeService.delete(grade.getGradeId());

        ModelAndView view = new ModelAndView("manage/ace/tables");

        return view;

    }
}
