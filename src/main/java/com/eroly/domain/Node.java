package com.eroly.domain;

public class Node {
    private Integer nodeId;

    private Integer nodeChapter;

    private String nodeName;

    private String nodeInfo;

    private String nodeIndex;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getNodeChapter() {
        return nodeChapter;
    }

    public void setNodeChapter(Integer nodeChapter) {
        this.nodeChapter = nodeChapter;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo == null ? null : nodeInfo.trim();
    }

    public String getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(String nodeIndex) {
        this.nodeIndex = nodeIndex == null ? null : nodeIndex.trim();
    }
}