package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TComprehensive;

public interface TComprehensiveMapper {
    int deleteByPrimaryKey(Long comprehensiveId);

    int insert(TComprehensive record);

    int insertSelective(TComprehensive record);

    TComprehensive selectByPrimaryKey(Long comprehensiveId);

    int updateByPrimaryKeySelective(TComprehensive record);

    int updateByPrimaryKeyWithBLOBs(TComprehensive record);

    int updateByPrimaryKey(TComprehensive record);
}