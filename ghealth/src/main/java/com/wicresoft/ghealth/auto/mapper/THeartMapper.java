package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.THeart;

public interface THeartMapper {
    int deleteByPrimaryKey(Long heartId);

    int insert(THeart record);

    int insertSelective(THeart record);

    THeart selectByPrimaryKey(Long heartId);

    int updateByPrimaryKeySelective(THeart record);

    int updateByPrimaryKey(THeart record);
}