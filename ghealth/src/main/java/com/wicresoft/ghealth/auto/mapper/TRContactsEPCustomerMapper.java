package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TRContactsEPCustomer;

public interface TRContactsEPCustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TRContactsEPCustomer record);

    int insertSelective(TRContactsEPCustomer record);

    TRContactsEPCustomer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TRContactsEPCustomer record);

    int updateByPrimaryKey(TRContactsEPCustomer record);
}