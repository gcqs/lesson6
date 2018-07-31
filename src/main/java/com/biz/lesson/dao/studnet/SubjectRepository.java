package com.biz.lesson.dao.studnet;


import com.biz.lesson.model.student.Subject;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface SubjectRepository extends PagingAndSortingRepository<Subject,Long>,JpaSpecificationExecutor<Subject> {

    @Modifying
    @Transactional
    @Query(value = "update usr_subject s set s.avgNum=:avgNum  where s.studentId = :studentId",nativeQuery = true)
    public void updateAvg(@Param("studentId")Long studentId, @Param("avgNum")int avgNum);

    public List<Subject> findByStudentId(@Param("studentId")Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from usr_subject where name=(select name from usr_subject where subjectId=:subjectId)",nativeQuery = true)
    public void deleteBySubjectId(@Param("subjectId")Long subjectId);



    @Query(value = "SELECT subjectId,avgNum ,name,studentId,COUNT(subjectId) AS count,sum(avgNum)/COUNT(subjectId) AS avg FROM usr_subject GROUP BY name ORDER BY count DESC",nativeQuery = true)
    List<Subject> findAll();

    @Modifying
    @Transactional
    @Query(value = "update usr_subject set name=:newName where name=:oldName",nativeQuery = true)
    public  void  updateByName(@Param("newName")String newName,@Param("oldName")String oldName);



}
