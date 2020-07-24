package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TEPCustomerData;

public interface TEPCustomerDataMapper {
    int deleteByPrimaryKey(Long epcustomerId);

    int insert(TEPCustomerData record);

    int insertSelective(TEPCustomerData record);

    TEPCustomerData selectByPrimaryKey(Long epcustomerId);

    int updateByPrimaryKeySelective(TEPCustomerData record);

    int updateByPrimaryKeyWithBLOBs(TEPCustomerData record);

    int updateByPrimaryKey(TEPCustomerData record);
}