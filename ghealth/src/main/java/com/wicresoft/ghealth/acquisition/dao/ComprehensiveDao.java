package com.wicresoft.ghealth.acquisition.dao;

import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.Comprehensive;

public interface ComprehensiveDao {

	Map<String, Object> getComprehensiveReport(Map<String, Object> param);
	
    void insertComprehensive(Comprehensive record);
    
    void updateComprehensive(Comprehensive record);
}
