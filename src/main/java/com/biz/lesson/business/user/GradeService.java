package com.biz.lesson.business.user;

import com.biz.lesson.dao.studnet.GradeRepository;
import com.biz.lesson.model.student.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class GradeService {


    @Autowired
    private GradeRepository gradeDao;

    //修改/添加
    public void add(Grade grade){
        gradeDao.save(grade);

    }

    //删除
    public void delete(Long id){
        gradeDao.deleteByGradeId(id);
    }

    //查找所有
    public Page<Grade> findAll(int page,int size){
        page = page -1;
        Pageable pageable = new PageRequest(page,size);

        Page<Grade> all = gradeDao.findAll(pageable);

        return all;

    }
}
