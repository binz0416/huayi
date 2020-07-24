package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TAction;

public interface TActionMapper {
    int deleteByPrimaryKey(String actionId);

    int insert(TAction record);

    int insertSelective(TAction record);

    TAction selectByPrimaryKey(String actionId);

    int updateByPrimaryKeySelective(TAction record);

    int updateByPrimaryKey(TAction record);
}