package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Teaching;
@Repository("TeachingMapper")
public interface TeachingMapper {
    int deleteByPrimaryKey(Integer teachingId);

    int insert(Teaching record);

    int insertSelective(Teaching record);

    Teaching selectByPrimaryKey(Integer teachingId);

    int updateByPrimaryKeySelective(Teaching record);

    int updateByPrimaryKey(Teaching record);
}