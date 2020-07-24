package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TMachine;

public interface TMachineMapper {
    int deleteByPrimaryKey(Long machineId);

    int insert(TMachine record);

    int insertSelective(TMachine record);

    TMachine selectByPrimaryKey(Long machineId);

    int updateByPrimaryKeySelective(TMachine record);

    int updateByPrimaryKey(TMachine record);
}