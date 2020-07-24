package com.wicresoft.ghealth.auto.mapper;

import com.wicresoft.ghealth.auto.pojo.TContacts;

public interface TContactsMapper {
    int deleteByPrimaryKey(Long contactsId);

    int insert(TContacts record);

    int insertSelective(TContacts record);

    TContacts selectByPrimaryKey(Long contactsId);

    int updateByPrimaryKeySelective(TContacts record);

    int updateByPrimaryKey(TContacts record);
}