package com.wicresoft.ghealth.catering.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.auto.pojo.TCatering;
import com.wicresoft.ghealth.auto.pojo.TCustomer;
import com.wicresoft.ghealth.auto.pojo.TSetMeal;

public interface CateringDao {

    List<TCustomer> selectByFaceid(Map<String,Object> param);
    
    List<TCatering> selectAll();
    
    List<TSetMeal> selectByUse(Map<String,Object> param);
    
    Map<String,Object> getCustomerMeals(Map<String,Object> param);
}
