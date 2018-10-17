package com.eroly.mapper;

import org.springframework.stereotype.Repository;

import com.eroly.domain.Price;
@Repository("PriceMapper")
public interface PriceMapper {
    int deleteByPrimaryKey(Integer priceId);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer priceId);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}