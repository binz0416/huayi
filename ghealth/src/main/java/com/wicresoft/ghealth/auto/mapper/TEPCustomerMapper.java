package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TEPCustomer;

public interface TEPCustomerMapper {
    int deleteByPrimaryKey(Long epcustomerId);

    int insert(TEPCustomer record);

    int insertSelective(TEPCustomer record);

    TEPCustomer selectByPrimaryKey(Long epcustomerId);

    int updateByPrimaryKeySelective(TEPCustomer record);

    int updateByPrimaryKey(TEPCustomer record);
}