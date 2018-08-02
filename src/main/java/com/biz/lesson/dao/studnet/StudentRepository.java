package com.biz.lesson.dao.studnet;

import com.biz.lesson.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface StudentRepository extends PagingAndSortingRepository<Student,Long>,JpaSpecificationExecutor<Student> {

    @Modifying
    @Transactional
    @Query(value = "delete from usr_student  where studentId=:studentId",nativeQuery = true)
    public void deleteByStudentId(@Param("studentId")Long studentId);

    public Student findByStudentId(@Param("studentId")Long studentId);

    @Modifying
    @Transactional
    @Query(value = "update usr_student s set s.avgNum=:avgNum  where s.studentId = :studentId",nativeQuery = true)
    public void updateAvg(@Param("studentId")Long studentId,@Param("avgNum")int avgNum);

    List<Student> findByGradeId(@Param("gradeId")Long gradeId);


    @Modifying
    @Transactional
    @Query(value = "update usr_student s set s.gradeId=:gradeId  where s.gradeId = :oldId",nativeQuery = true)
    public void updateGrade(@Param("gradeId")Long gradeId,@Param("oldId")Long oldId);

    @Modifying
    @Transactional
    @Query(value = "update usr_student s set s.subjectNum = s.subjectNum+ :subjectNum where s.studentId = :studentId",nativeQuery = true)
    public void updateSubject(@Param("studentId")Long studentId,@Param("subjectNum")int subjectNum);
}
