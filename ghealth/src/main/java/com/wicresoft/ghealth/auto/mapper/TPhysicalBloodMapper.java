package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TPhysicalBlood;

public interface TPhysicalBloodMapper {
    int deleteByPrimaryKey(Long bloodId);

    int insert(TPhysicalBlood record);

    int insertSelective(TPhysicalBlood record);

    TPhysicalBlood selectByPrimaryKey(Long bloodId);

    int updateByPrimaryKeySelective(TPhysicalBlood record);

    int updateByPrimaryKey(TPhysicalBlood record);
}