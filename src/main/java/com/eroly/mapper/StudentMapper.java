package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Student;
@Repository("StudentMapper")
public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    Map<String, Object> findSignUp(Map<String, String> param);
    List<Map<String, Object>> findSignByUser(String userId);
    /**
     * 插入报名信息
     * @param param (userId用户ID,courseId课程ID,hasPay是否缴费,payMoney缴费金额,studentStatus学员状态)
     * @return
     */
    int insertSignInfo(Map<String,String> param);
}