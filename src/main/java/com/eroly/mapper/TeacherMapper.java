package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Teacher;
@Repository("TeacherMapper")
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    /**
     * 查询所有教师信息
     * @return
     */
    List<Map<String,Object>> findAll();
}