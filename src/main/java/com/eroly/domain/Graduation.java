package com.eroly.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Graduation {
    private Integer graduationId;

    private Integer graduationUser;

    private Date graduationDatetime;

    private String graduationJob;

    private String graduationPlace;

    private BigDecimal graduationSalary;

    private Date graduationJobtime;

    private String graduationStatus;

    public Integer getGraduationId() {
        return graduationId;
    }

    public void setGraduationId(Integer graduationId) {
        this.graduationId = graduationId;
    }

    public Integer getGraduationUser() {
        return graduationUser;
    }

    public void setGraduationUser(Integer graduationUser) {
        this.graduationUser = graduationUser;
    }

    public Date getGraduationDatetime() {
        return graduationDatetime;
    }

    public void setGraduationDatetime(Date graduationDatetime) {
        this.graduationDatetime = graduationDatetime;
    }

    public String getGraduationJob() {
        return graduationJob;
    }

    public void setGraduationJob(String graduationJob) {
        this.graduationJob = graduationJob == null ? null : graduationJob.trim();
    }

    public String getGraduationPlace() {
        return graduationPlace;
    }

    public void setGraduationPlace(String graduationPlace) {
        this.graduationPlace = graduationPlace == null ? null : graduationPlace.trim();
    }

    public BigDecimal getGraduationSalary() {
        return graduationSalary;
    }

    public void setGraduationSalary(BigDecimal graduationSalary) {
        this.graduationSalary = graduationSalary;
    }

    public Date getGraduationJobtime() {
        return graduationJobtime;
    }

    public void setGraduationJobtime(Date graduationJobtime) {
        this.graduationJobtime = graduationJobtime;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus == null ? null : graduationStatus.trim();
    }
}