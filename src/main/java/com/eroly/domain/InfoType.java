package com.eroly.domain;

public class InfoType {
    private Integer infoTypeId;

    private String infoTypeName;

    public Integer getInfoTypeId() {
        return infoTypeId;
    }

    public void setInfoTypeId(Integer infoTypeId) {
        this.infoTypeId = infoTypeId;
    }

    public String getInfoTypeName() {
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName) {
        this.infoTypeName = infoTypeName == null ? null : infoTypeName.trim();
    }
}