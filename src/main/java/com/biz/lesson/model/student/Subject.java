package com.biz.lesson.model.student;

import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 课程
 */
@Entity
@Table(name = "usr_subject")
public class Subject {

    /*
    课程Id
     */
    @Id
    @Column(length = 20,nullable = false)
    private Long subjectId;

    /*
    课程名
     */
    @Column(length = 20,nullable = false)
    private String name;

    /*
    选课的学生id
     */
    @Column(length = 20,nullable = false)
    private Long studentId;

    /*
    选课学生的分数
     */
    @Column(length = 10,nullable = false)
    private int avgNum;

    @Transient
    private int count;

    @Transient
    private int avg;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvgNum() {
        return avgNum;
    }

    public void setAvgNum(int avgNum) {
        this.avgNum = avgNum;
    }
}
