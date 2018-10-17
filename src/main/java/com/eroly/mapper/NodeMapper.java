package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Node;
@Repository("NodeMapper")
public interface NodeMapper {
    int deleteByPrimaryKey(Integer nodeId);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer nodeId);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);
    List<Map<String, Object>> findNodeByChapter(String chapterId);
}