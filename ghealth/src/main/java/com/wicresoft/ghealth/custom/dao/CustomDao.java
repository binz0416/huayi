package com.wicresoft.ghealth.custom.dao;

import com.wicresoft.ghealth.auto.pojo.TCustomer;
import com.wicresoft.ghealth.auto.pojo.TOrganization;

public interface CustomDao {
	
    TOrganization selectByOrganizationId(String organizationNo);
	
    TCustomer selectByIdcard(String idcard);
    

}
