package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TCatering;

public interface TCateringMapper {
    int deleteByPrimaryKey(Long cateringId);

    int insert(TCatering record);

    int insertSelective(TCatering record);

    TCatering selectByPrimaryKey(Long cateringId);

    int updateByPrimaryKeySelective(TCatering record);

    int updateByPrimaryKey(TCatering record);
}