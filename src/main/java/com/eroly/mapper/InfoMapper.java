package com.eroly.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Info;
@Repository("InfoMapper")
public interface InfoMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);
    List<Map<String, Object>> selectByInfoType(String infoType);
    List<String> selectInfoType();
}