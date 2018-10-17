package com.eroly.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroly.domain.User;
@Repository("UserMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    Map<String, Object> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    Map<String,Object> findByUserName(String userName);
    /**
     * 根据用户的信息查询用户
     * @param userInfo
     * @return
     */
    Map<String,Object> findByUserInfo(String userInfo);
}