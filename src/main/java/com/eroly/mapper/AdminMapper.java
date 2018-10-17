package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Admin;
@Repository("AdminMapper")
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}