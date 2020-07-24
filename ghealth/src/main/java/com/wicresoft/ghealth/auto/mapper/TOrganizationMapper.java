package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TOrganization;

public interface TOrganizationMapper {
    int deleteByPrimaryKey(String organizationId);

    int insert(TOrganization record);

    int insertSelective(TOrganization record);

    TOrganization selectByPrimaryKey(String organizationId);

    int updateByPrimaryKeySelective(TOrganization record);

    int updateByPrimaryKey(TOrganization record);
}