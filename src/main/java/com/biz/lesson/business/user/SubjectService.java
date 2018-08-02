package com.biz.lesson.business.user;

import com.biz.lesson.dao.studnet.GradeRepository;
import com.biz.lesson.dao.studnet.StudentRepository;
import com.biz.lesson.dao.studnet.SubjectRepository;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Iterator;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectDao;

    @Autowired
    private StudentRepository studentDao;

    @Autowired
    private GradeRepository gradeDao;

    //添加
    public void add(Subject  subject){
        subjectDao.save(subject);

    }

    public void update(Subject subject){

        List<Subject> byStudentId = subjectDao.findBySubjectId(subject.getSubjectId());
        String oldName = byStudentId.get(0).getName();

        subjectDao.updateByName(subject.getName(),oldName);
    }

    //删除
    public void delete(Long id){

        List<Subject> bySubjectId = subjectDao.findBySubjectId(id);
        subjectDao.deleteByName(bySubjectId.get(0).getName());


        Iterable<Student> list = studentDao.findAll();
        Iterator<Student> iterator = list.iterator();

        //更新平均分
        while (iterator.hasNext()){
            Student student = iterator.next();
            List<Subject> byStudentId = subjectDao.findByStudentId(student.getStudentId());
            int i = 0;
            if(!byStudentId.isEmpty()||byStudentId!=null){


            for (Subject subject:byStudentId){
                i = i+subject.getAvgNum();
            }
            studentDao.updateAvg(student.getStudentId(),i);


            }else {

                studentDao.updateAvg(student.getStudentId(),0);
            }

            studentDao.updateSubject(student.getStudentId(),-1);
            gradeDao.updateByGradeId(student.getGradeId(),0);



        }



    }

    //查找所有
    public List<Subject> findAll(){

        List<Subject> all = subjectDao.findAll();
        for(Subject subject : all){
            int count = subject.getCount();
            subject.setCount(count-1);
            int avg = subject.getAvg();
            if(count!=1){

                subject.setAvg(avg/(count-1));

            }else{
                subject.setAvg(0);
            }
        }

        return all;
    }


}
