package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TActlog;

public interface TActlogMapper {
    int deleteByPrimaryKey(Long actlogId);

    int insert(TActlog record);

    int insertSelective(TActlog record);

    TActlog selectByPrimaryKey(Long actlogId);

    int updateByPrimaryKeySelective(TActlog record);

    int updateByPrimaryKeyWithBLOBs(TActlog record);

    int updateByPrimaryKey(TActlog record);
}