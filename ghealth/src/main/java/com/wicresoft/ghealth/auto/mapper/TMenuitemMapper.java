package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TMenuitem;

public interface TMenuitemMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(TMenuitem record);

    int insertSelective(TMenuitem record);

    TMenuitem selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(TMenuitem record);

    int updateByPrimaryKey(TMenuitem record);
}