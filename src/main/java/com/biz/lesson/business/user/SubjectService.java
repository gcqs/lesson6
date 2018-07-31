package com.biz.lesson.business.user;

import com.biz.lesson.dao.studnet.SubjectRepository;
import com.biz.lesson.model.student.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectDao;

    //添加
    public void add(Subject  subject){
        subjectDao.save(subject);

    }

    public void update(Subject subject){


        List<Subject> byStudentId = subjectDao.findByStudentId(subject.getStudentId());
        String oldName = byStudentId.get(0).getName();

        subjectDao.updateByName(subject.getName(),oldName);
    }

    //删除
    public void delete(Long id){

        subjectDao.deleteBySubjectId(id);

    }

    //查找所有
    public List<Subject> findAll(){

        List<Subject> all = subjectDao.findAll();
        for(Subject subject : all){
            int count = subject.getCount();
            subject.setCount(count-1);
            int avg = subject.getAvg();
            if(count!=1){

                subject.setAvg(avg*count/(count-1));

            }else{
                subject.setAvg(0);
            }
        }

        return all;
    }


}
