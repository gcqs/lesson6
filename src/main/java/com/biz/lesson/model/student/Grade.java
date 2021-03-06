package com.biz.lesson.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 班级
 */
@Entity
@Table(name = "usr_grade")
public class Grade {

    /*
    班级ID
     */
    @Id
    @Column(length = 20,nullable = false)
    private Long gradeId;

    /*
    班级名
     */
    @Column(length = 20,nullable = false)
    private String name;

    @Column(length = 10,nullable = false)
    private int num;

    /*
    平均数
     */
    @Column(length = 10,nullable = false)
    private int avgNum;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public int getAvgNum() {
        return avgNum;
    }

    public void setAvgNum(int avgNum) {
        this.avgNum = avgNum;
    }
}
