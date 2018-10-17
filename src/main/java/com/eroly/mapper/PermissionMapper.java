package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Permission;
@Repository("PermissionMapper")
public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}