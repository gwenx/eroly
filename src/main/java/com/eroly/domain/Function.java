package com.eroly.domain;

public class Function {
    private Integer functionId;

    private String functionName;

    private String functionPath;

    private String functionInfo;

    private Integer functionLv;

    private String functionTarget;

    private Integer funPid;

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath == null ? null : functionPath.trim();
    }

    public String getFunctionInfo() {
        return functionInfo;
    }

    public void setFunctionInfo(String functionInfo) {
        this.functionInfo = functionInfo == null ? null : functionInfo.trim();
    }

    public Integer getFunctionLv() {
        return functionLv;
    }

    public void setFunctionLv(Integer functionLv) {
        this.functionLv = functionLv;
    }

    public String getFunctionTarget() {
        return functionTarget;
    }

    public void setFunctionTarget(String functionTarget) {
        this.functionTarget = functionTarget == null ? null : functionTarget.trim();
    }

    public Integer getFunPid() {
        return funPid;
    }

    public void setFunPid(Integer funPid) {
        this.funPid = funPid;
    }
}