package com.biz.lesson.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 学生
 */
@Entity
@Table(name = "usr_student")
public class Student {

    /*
    学生id
     */
    @Id
    @Column(length = 20,nullable = false)
    private Long studentId;

    /*
    学号
     */
    @Column(length = 20,nullable = false)
    private int num;

    /*
    姓名
     */
    @Column(length = 50,nullable = false)
    private String name;

    /*
    性别
     */
    @Column(length = 2,nullable = false)
    private String sex;

    /*
    出生日期
     */
    @Column(length = 20)
    private Date date;

    /*
    所在班级名
     */
    @Column(length = 20)
    private Long gradeId;

    /*
    所选科目数
     */
    @Column(length = 10)
    private int subjectNum;

    /*
    平均分
     */
    @Column(length = 20)
    private int avgNum;

    /*
    头像
     */
    @Column(length = 100)
    private String img;



    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAvgNum() {
        return avgNum;
    }

    public void setAvgNum(int avgNum) {
        this.avgNum = avgNum;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public int getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(int subjectNum) {
        this.subjectNum = subjectNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
