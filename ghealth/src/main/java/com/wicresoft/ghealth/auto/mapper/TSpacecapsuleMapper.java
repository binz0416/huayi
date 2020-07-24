package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TSpacecapsule;

public interface TSpacecapsuleMapper {
    int deleteByPrimaryKey(Long spacecapsuleId);

    int insert(TSpacecapsule record);

    int insertSelective(TSpacecapsule record);

    TSpacecapsule selectByPrimaryKey(Long spacecapsuleId);

    int updateByPrimaryKeySelective(TSpacecapsule record);

    int updateByPrimaryKey(TSpacecapsule record);
}