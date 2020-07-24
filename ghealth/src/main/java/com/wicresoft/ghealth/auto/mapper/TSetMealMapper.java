package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TSetMeal;
import com.wicresoft.ghealth.auto.pojo.TSetMealWithBLOBs;

public interface TSetMealMapper {
    int deleteByPrimaryKey(Long setMealId);

    int insert(TSetMealWithBLOBs record);

    int insertSelective(TSetMealWithBLOBs record);

    TSetMealWithBLOBs selectByPrimaryKey(Long setMealId);

    int updateByPrimaryKeySelective(TSetMealWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TSetMealWithBLOBs record);

    int updateByPrimaryKey(TSetMeal record);
}