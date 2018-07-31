package com.biz.lesson.business.user;

import com.biz.lesson.dao.studnet.GradeRepository;
import com.biz.lesson.dao.studnet.StudentRepository;
import com.biz.lesson.dao.studnet.SubjectRepository;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class StudentManager {
//
    @Autowired
    private StudentRepository studentDao;

    @Autowired
    private SubjectRepository subjectDao;

    @Autowired
    private GradeRepository gradeDao;



    public Page<Student> findAll (int page){

        page = page -1;
        Pageable pageable = new PageRequest(page,5);

        Page all = studentDao.findAll(pageable);

        System.out.println("============="+all.getContent());

        return all;
    }


    //按日期查询
    public Page<Student> findByDate(int page, Student student, Date date1, Date date2) {

        Pageable pageable = new PageRequest(page,5);
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path studentId = root.get("studentId");
                Path name = root.get("name");
                Path birthday = root.get("birthday");
                Predicate like = criteriaBuilder.like(studentId, student.getStudentId() + "%");
                Predicate like1 = criteriaBuilder.like(name, "%" + student.getName() + "%");

                Predicate predicate = criteriaBuilder.lessThan(birthday, date2);
                Predicate predicate1 = criteriaBuilder.greaterThan(birthday, date1);

                Predicate and = criteriaBuilder.and(like, like1, predicate1, predicate);

                return and;
            }
        };

        Page all = studentDao.findAll(specification, pageable);

        return all;
    }

//    添加
    public void add(Student student){
        studentDao.save(student);
        Long gradeId = student.getGradeId();

        //System.out.println("+++++++++++++"+gradeId);
        gradeDao.updateByGradeId(gradeId,1);

    }
    //修改

    public void update(Student student){


        Student student1 = studentDao.findByStudentId(student.getStudentId());
        student.setAvgNum(student1.getAvgNum());
        student.setSubjectNum(student1.getSubjectNum());
        studentDao.save(student);

    }
    //删除
    public void delete(Student student){


        studentDao.deleteByStudentId(student.getStudentId());
        List<Subject> list = subjectDao.findByStudentId(student.getStudentId());
        for(Subject subject: list){
            subjectDao.deleteBySubjectId(subject.getSubjectId());

        }


        gradeDao.updateByGradeId(student.getGradeId(),-1);

    }


    //已选课程
    public List<Subject> findSubject(Long id){

        List<Subject> list = subjectDao.findByStudentId(id);


        return list;
    }

    //录入
    public void updateAvg(Student student){

        Long studentId = student.getStudentId();
        Student student1 = studentDao.findByStudentId(studentId);

        student1.setAvgNum(student.getAvgNum());
        Long gradeId = student1.getGradeId();

        studentDao.save(student1);
        gradeDao.updateByGradeId(gradeId,0);
    }

    public void addAvg(Subject subject){
        IdWorker worker2 = new IdWorker(2);
        subject.setSubjectId(worker2.nextId());
        subjectDao.save(subject);

    }


    //未选课程
    public List<Subject> findNoSubject(String id){


        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path studentId = root.get("studentId");
                Predicate notEqual = criteriaBuilder.notEqual(studentId, id);

                Predicate and = criteriaBuilder.and(notEqual);

                return and;
            }
        };

        List<Subject> all = subjectDao.findAll(specification);

        return all;
    }

    //选课
    public  void addSubject(Subject subject){
        subjectDao.save(subject);

    }



}
