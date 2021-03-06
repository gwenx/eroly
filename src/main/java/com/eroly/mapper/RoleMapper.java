package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Role;
@Repository("RoleMapper")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}