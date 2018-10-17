package com.eroly.domain;

import java.util.Date;

public class Info {
    private Integer infoId;

    private Integer infoAdmin;

    private String infoTitle;

    private Integer infoType;

    private String infoContrnt;

    private Date infoDate;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getInfoAdmin() {
        return infoAdmin;
    }

    public void setInfoAdmin(Integer infoAdmin) {
        this.infoAdmin = infoAdmin;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle == null ? null : infoTitle.trim();
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getInfoContrnt() {
        return infoContrnt;
    }

    public void setInfoContrnt(String infoContrnt) {
        this.infoContrnt = infoContrnt == null ? null : infoContrnt.trim();
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }
}