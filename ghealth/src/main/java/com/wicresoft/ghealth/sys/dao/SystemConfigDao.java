package com.wicresoft.ghealth.sys.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.sys.dto.Config;;

public interface SystemConfigDao {
	
	List<Map<String, Object>> getConfig();
		
	void insertConfig(Config record);
	
	void updateConfig(Config record);
	
	
}