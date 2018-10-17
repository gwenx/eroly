package com.eroly.domain;

import java.util.Date;

public class Teaching {
    private Integer teachingId;

    private Integer teachingTeacher;

    private Integer teachingCourse;

    private Date teachingStart;

    private Date teachingEnd;

    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
    }

    public Integer getTeachingTeacher() {
        return teachingTeacher;
    }

    public void setTeachingTeacher(Integer teachingTeacher) {
        this.teachingTeacher = teachingTeacher;
    }

    public Integer getTeachingCourse() {
        return teachingCourse;
    }

    public void setTeachingCourse(Integer teachingCourse) {
        this.teachingCourse = teachingCourse;
    }

    public Date getTeachingStart() {
        return teachingStart;
    }

    public void setTeachingStart(Date teachingStart) {
        this.teachingStart = teachingStart;
    }

    public Date getTeachingEnd() {
        return teachingEnd;
    }

    public void setTeachingEnd(Date teachingEnd) {
        this.teachingEnd = teachingEnd;
    }
}