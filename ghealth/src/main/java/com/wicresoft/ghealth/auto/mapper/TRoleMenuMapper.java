package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TRoleMenu;

public interface TRoleMenuMapper {
    int deleteByPrimaryKey(Long connectionid);

    int insert(TRoleMenu record);

    int insertSelective(TRoleMenu record);

    TRoleMenu selectByPrimaryKey(Long connectionid);

    int updateByPrimaryKeySelective(TRoleMenu record);

    int updateByPrimaryKey(TRoleMenu record);
}