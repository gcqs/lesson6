package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.GradeService;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.util.IdWorker;
import com.biz.lesson.vo.studentVo.GradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * 班级
 */

@Controller
@RequestMapping(value = "manage/grade")
public class GradeController {


    @Autowired
    private GradeService gradeService;


    //显示所有
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

    //添加
    @RequestMapping("/add")
    public void add(Grade grade,HttpServletResponse response) throws  Exception{

//        System.out.println(grade.getName()+"=====================================");
        IdWorker worker2 = new IdWorker(2);
        grade.setGradeId(worker2.nextId());
        grade.setAvgNum(0);
        grade.setNum(0);
        gradeService.add(grade);
        response.sendRedirect("/manage/grade/all.do");

    }

    //修改
    @RequestMapping("/update")
    public ModelAndView update(GradeVo gradeVo){

        ModelAndView view = new ModelAndView("manage/ace/gradeUpdate");

        view.addObject("id",gradeVo.getGradeId());

        return view;

    }

    //保存修改
    @RequestMapping("/save_update")
    public void save_update(Grade grade,HttpServletResponse response) throws Exception{

        gradeService.add(grade);


        response.sendRedirect("/manage/grade/all.do");

    }
    //删除
    @RequestMapping("/delete")
    public void delete(Grade grade, HttpServletResponse response)throws Exception{

       gradeService.delete(grade.getGradeId());

        response.sendRedirect("/manage/grade/all.do");

    }
}
