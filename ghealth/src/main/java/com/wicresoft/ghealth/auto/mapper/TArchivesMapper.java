package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TArchives;

public interface TArchivesMapper {
    int deleteByPrimaryKey(String archivesId);

    int insert(TArchives record);

    int insertSelective(TArchives record);

    TArchives selectByPrimaryKey(String archivesId);

    int updateByPrimaryKeySelective(TArchives record);

    int updateByPrimaryKeyWithBLOBs(TArchives record);

    int updateByPrimaryKey(TArchives record);
}