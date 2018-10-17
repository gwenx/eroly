package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Course;
@Repository("CourseMapper")
public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    /**
     * 查询课程信息
     * @return
     */
    List<Map<String, Object>> findCourseInfo();
    /**
     * 根据关键词查找课程ID
     * @param keyWords
     * @return
     */
    List<String> findCourseIdByKeyWords(String keyWords);
}