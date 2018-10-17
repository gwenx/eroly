package com.eroly.domain;

public class Chapter {
    private Integer chapterId;

    private Integer chapterCourse;

    private String chapterName;

    private String chapterInfo;

    private Integer chapterIndex;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterCourse() {
        return chapterCourse;
    }

    public void setChapterCourse(Integer chapterCourse) {
        this.chapterCourse = chapterCourse;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getChapterInfo() {
        return chapterInfo;
    }

    public void setChapterInfo(String chapterInfo) {
        this.chapterInfo = chapterInfo == null ? null : chapterInfo.trim();
    }

    public Integer getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
    }
}