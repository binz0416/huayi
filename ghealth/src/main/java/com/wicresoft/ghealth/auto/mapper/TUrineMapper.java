package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TUrine;

public interface TUrineMapper {
    int deleteByPrimaryKey(Long urineId);

    int insert(TUrine record);

    int insertSelective(TUrine record);

    TUrine selectByPrimaryKey(Long urineId);

    int updateByPrimaryKeySelective(TUrine record);

    int updateByPrimaryKey(TUrine record);
}