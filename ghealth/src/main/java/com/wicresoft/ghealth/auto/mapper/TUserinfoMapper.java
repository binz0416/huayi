package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TUserinfo;

public interface TUserinfoMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(TUserinfo record);

    int insertSelective(TUserinfo record);

    TUserinfo selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(TUserinfo record);

    int updateByPrimaryKey(TUserinfo record);
}