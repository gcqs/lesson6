package com.biz.lesson.business.user;

import com.biz.lesson.dao.studnet.GradeRepository;
import com.biz.lesson.dao.studnet.StudentRepository;
import com.biz.lesson.dao.studnet.SubjectRepository;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.util.IdWorker;
import com.biz.lesson.vo.studentVo.StudentVo;
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
        List<Student> list = all.getContent();

//        System.out.println("============="+all.getContent());

        return all;
    }

    public StudentVo findStudent(Long studentId){
        Student student = studentDao.findByStudentId(studentId);
        StudentVo studentVo = new StudentVo();
        studentVo.setAvgNum(student.getAvgNum());
        studentVo.setDate(student.getDate());
        studentVo.setGradeId(student.getGradeId());
        studentVo.setImg(student.getImg());
        studentVo.setName(student.getName());
        studentVo.setNum(student.getNum());
        studentVo.setSex(student.getSex());
        studentVo.setStudentId(student.getStudentId());
        studentVo.setSubjectNum(student.getSubjectNum());
        return studentVo;

    }


    //按日期查询
    public Page<Student> findByDate(int page, Student student, Date date1, Date date2) {

        Pageable pageable = new PageRequest(page,5);
//        System.out.println(student.getName()+"++++++++++++======++++"+student.getNum()+"---------------"+date1.getTime());
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path num = root.get("num");
                Path name = root.get("name");
                Path date = root.get("date");
                int num1 = student.getNum();
                Predicate like = criteriaBuilder.like(num,  String.valueOf(num1)+ "%");
                Predicate like1 = criteriaBuilder.like(name, "%" + student.getName() + "%");
                Predicate predicate = criteriaBuilder.lessThan(date, date2);
                Predicate predicate1 = criteriaBuilder.greaterThan(date, date1);

                Predicate and = criteriaBuilder.and( like1, predicate1, predicate);

                return and;
            }
        };

        Page all = studentDao.findAll(specification, pageable);
//        System.out.println(all.getContent()+"+++++++++++all++++++++++");

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


//        System.out.println(list+"==========list+==========");
            subjectDao.deleteByStudentId(student.getStudentId());


        gradeDao.updateByGradeId(student.getGradeId(),-1);

        studentDao.deleteByStudentId(student.getStudentId());

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


        List<Subject> list = subjectDao.findByStudentId(Long.valueOf(id));
        List<Subject> list2 = subjectDao.findByStudentId(Long.valueOf(0));
        for(int i =0;i<list.size();i++){

            for(int j=0; j<list2.size();j++){
//                System.out.println(list2.get(j).getName()+"+++++++++++++++++++=++++++++++++++"+list.get(i).getName());
                if(list.get(i).getName().equals(list2.get(j).getName())){


//                    System.out.println(list2.get(j).getName()+"+++++++++++++=====+++++++++++++++++");
                    list2.remove(j);


                }

            }
        }

        return list2;
    }

    //选课
    public  void addSubject(Subject subject){
        subjectDao.save(subject);
        studentDao.updateSubject(subject.getStudentId(),+1);

    }



}
