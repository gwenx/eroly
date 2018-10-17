package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.InfoType;
@Repository("InfoTypeMapper")
public interface InfoTypeMapper {
    int deleteByPrimaryKey(Integer infoTypeId);

    int insert(InfoType record);

    int insertSelective(InfoType record);

    InfoType selectByPrimaryKey(Integer infoTypeId);

    int updateByPrimaryKeySelective(InfoType record);

    int updateByPrimaryKey(InfoType record);
}