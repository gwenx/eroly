package com.eroly.domain;

import java.util.Date;

public class Student {
    private Integer studentId;

    private Integer studentUser;

    private Integer studentCourse;

    private Date studentDatetime;

    private String studentPay;

    private Double studentMoney;

    private String studentStatus;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(Integer studentUser) {
        this.studentUser = studentUser;
    }

    public Integer getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(Integer studentCourse) {
        this.studentCourse = studentCourse;
    }

    public Date getStudentDatetime() {
        return studentDatetime;
    }

    public void setStudentDatetime(Date studentDatetime) {
        this.studentDatetime = studentDatetime;
    }

    public String getStudentPay() {
        return studentPay;
    }

    public void setStudentPay(String studentPay) {
        this.studentPay = studentPay == null ? null : studentPay.trim();
    }

    public Double getStudentMoney() {
        return studentMoney;
    }

    public void setStudentMoney(Double studentMoney) {
        this.studentMoney = studentMoney;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus == null ? null : studentStatus.trim();
    }
}