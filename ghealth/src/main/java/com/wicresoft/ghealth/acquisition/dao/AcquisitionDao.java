package com.wicresoft.ghealth.acquisition.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.PhysicalBloodDto;
import com.wicresoft.ghealth.acquisition.dto.PhysicalUltrasoundDto;
import com.wicresoft.ghealth.acquisition.dto.PhysicalUrineDto;
import com.wicresoft.ghealth.auto.pojo.TSpacecapsule;

public interface AcquisitionDao {
	
    int addHeart(Map<String, Object> record);
    
    List<Map<String, Object>> selectMachineByType(int machineType);
    
    int saveMachineLog(Map<String, Object> machineLog);

    Map<String, Object> getCustomerId(Map<String, Object> param);
    
    List<TSpacecapsule> getReportIdByTime(Map<String,Object> param); 
    
    Map<String, Object> getToxin(Map<String,Object> param);
    
    List<Map<String, Object>> getPhysicalDetail(Map<String,Object> param);
    
    int deleteByToxinDetail(Long toxinId);
    
    int deleteByToxinExDetail(Long toxinId);
    
    PhysicalBloodDto getPhysicalBloodInfos(Map<String,Object> param);
    
    void insertPhysicalBlood(PhysicalBloodDto record);
    
    void updatePhysicalBlood(PhysicalBloodDto record);
    
    PhysicalUltrasoundDto getPhysicalUltrasoundInfos(Map<String,Object> param);
    
    void insertPhysicalUltrasound(PhysicalUltrasoundDto record);
    
    void updatePhysicalUltrasound(PhysicalUltrasoundDto record);
    
    PhysicalUrineDto getPhysicalUrineInfos(Map<String,Object> param);
    
    void insertPhysicalUrine(PhysicalUrineDto record);
    
    void updatePhysicalUrine(PhysicalUrineDto record);
    

    
    
    
}
