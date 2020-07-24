package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TOptions;

public interface TOptionsMapper {
    int deleteByPrimaryKey(Long optionsId);

    int insert(TOptions record);

    int insertSelective(TOptions record);

    TOptions selectByPrimaryKey(Long optionsId);

    int updateByPrimaryKeySelective(TOptions record);

    int updateByPrimaryKey(TOptions record);
}