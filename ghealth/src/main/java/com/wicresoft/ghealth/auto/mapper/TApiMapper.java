package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TApi;

public interface TApiMapper {
    int deleteByPrimaryKey(Long apiId);

    int insert(TApi record);

    int insertSelective(TApi record);

    TApi selectByPrimaryKey(Long apiId);

    int updateByPrimaryKeySelective(TApi record);

    int updateByPrimaryKey(TApi record);
}