package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Graduation;
@Repository("GraduationMapper")
public interface GraduationMapper {
    int deleteByPrimaryKey(Integer graduationId);

    int insert(Graduation record);

    int insertSelective(Graduation record);

    Graduation selectByPrimaryKey(Integer graduationId);

    int updateByPrimaryKeySelective(Graduation record);

    int updateByPrimaryKey(Graduation record);
    List<Map<String,Object>> findTopSixGra();
    /**
     * 查询就业信息
     * @return
     */
    List<Map<String,Object>> findGraOfPage();
    /**
     * 查询就业信息个数
     * @return
     */
    long findGraCount();
}