package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TCustomerCheckin;

public interface TCustomerCheckinMapper {
    int deleteByPrimaryKey(Long connectionid);

    int insert(TCustomerCheckin record);

    int insertSelective(TCustomerCheckin record);

    TCustomerCheckin selectByPrimaryKey(Long connectionid);

    int updateByPrimaryKeySelective(TCustomerCheckin record);

    int updateByPrimaryKey(TCustomerCheckin record);
}