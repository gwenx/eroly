package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Function;
@Repository("FunctionMapper")
public interface FunctionMapper {
    int deleteByPrimaryKey(Integer functionId);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer functionId);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}