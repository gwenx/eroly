package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Chapter;
@Repository("ChapterMapper")
public interface ChapterMapper {
    int deleteByPrimaryKey(Integer chapterId);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer chapterId);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);
    List<Map<String, Object>> findByCourseId(String courseId);
}