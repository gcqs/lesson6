package com.biz.lesson.dao.studnet;

import com.biz.lesson.model.student.Grade;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GradeRepository extends PagingAndSortingRepository<Grade,Long>,JpaSpecificationExecutor<Grade> {

//
    @Modifying
    @Transactional
    @Query(value = "delete from usr_grade where gradeId=:gradeId",nativeQuery = true)
    public void deleteByGradeId(@Param("gradeId")Long gradeId);

    @Modifying
    @Transactional
    @Query(value = "update usr_grade set num = num + :i,avgNum = (select avg(avgNum) from usr_student) where  gradeId=:gradeId",nativeQuery = true)
    public void updateByGradeId(@Param("gradeId")Long gradeId,@Param("i")int i);

//    @Modifying
//    @Query(value = "update grade set  where gradeId=:gradeId",nativeQuery = true)
//    public void updateAvg(@Param("gradeId")Long gradeId);
}
