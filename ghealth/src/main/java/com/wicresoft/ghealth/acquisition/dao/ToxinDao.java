package com.wicresoft.ghealth.acquisition.dao;

import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.Toxin;
import com.wicresoft.ghealth.acquisition.dto.ToxinAbnormal;
import com.wicresoft.ghealth.acquisition.dto.ToxinDetected;

public interface ToxinDao {
	
	Map<String, Object> getToxinReport(Map<String, Object> param);
	
	Long getToxinId(Toxin param);
	
    void insertToxin(Toxin record);
    
    void updateToxin(Toxin record);
    
    void deleteToxinDetected(Long toxinId);
    
    void deleteToxinAbnormal(Long toxinId);
    
    void insertDetected(ToxinDetected record);
    
    void insertAbnormal(ToxinAbnormal record);
    
}
