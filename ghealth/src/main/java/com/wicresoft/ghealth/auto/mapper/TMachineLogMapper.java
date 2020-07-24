package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TMachineLog;

public interface TMachineLogMapper {
    int deleteByPrimaryKey(Long machineLogId);

    int insert(TMachineLog record);

    int insertSelective(TMachineLog record);

    TMachineLog selectByPrimaryKey(Long machineLogId);

    int updateByPrimaryKeySelective(TMachineLog record);

    int updateByPrimaryKeyWithBLOBs(TMachineLog record);

    int updateByPrimaryKey(TMachineLog record);
}