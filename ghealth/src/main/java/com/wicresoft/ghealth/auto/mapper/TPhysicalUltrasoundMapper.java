package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TPhysicalUltrasound;
import com.wicresoft.ghealth.auto.pojo.TPhysicalUltrasoundWithBLOBs;

public interface TPhysicalUltrasoundMapper {
    int deleteByPrimaryKey(Long ultrasoundId);

    int insert(TPhysicalUltrasoundWithBLOBs record);

    int insertSelective(TPhysicalUltrasoundWithBLOBs record);

    TPhysicalUltrasoundWithBLOBs selectByPrimaryKey(Long ultrasoundId);

    int updateByPrimaryKeySelective(TPhysicalUltrasoundWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TPhysicalUltrasoundWithBLOBs record);

    int updateByPrimaryKey(TPhysicalUltrasound record);
}