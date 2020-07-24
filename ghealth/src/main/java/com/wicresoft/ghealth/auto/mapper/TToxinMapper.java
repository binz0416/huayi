package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TToxin;
import com.wicresoft.ghealth.auto.pojo.TToxinWithBLOBs;

public interface TToxinMapper {
    int deleteByPrimaryKey(Long toxinId);

    int insert(TToxinWithBLOBs record);

    int insertSelective(TToxinWithBLOBs record);

    TToxinWithBLOBs selectByPrimaryKey(Long toxinId);

    int updateByPrimaryKeySelective(TToxinWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TToxinWithBLOBs record);

    int updateByPrimaryKey(TToxin record);
}