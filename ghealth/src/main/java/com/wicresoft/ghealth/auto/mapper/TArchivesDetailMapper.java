package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TArchivesDetail;

public interface TArchivesDetailMapper {
    int deleteByPrimaryKey(Long archivesDetailId);

    int insert(TArchivesDetail record);

    int insertSelective(TArchivesDetail record);

    TArchivesDetail selectByPrimaryKey(Long archivesDetailId);

    int updateByPrimaryKeySelective(TArchivesDetail record);

    int updateByPrimaryKeyWithBLOBs(TArchivesDetail record);

    int updateByPrimaryKey(TArchivesDetail record);
}