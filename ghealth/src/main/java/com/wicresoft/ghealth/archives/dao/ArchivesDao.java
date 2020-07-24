package com.wicresoft.ghealth.archives.dao;

import java.util.List;
import java.util.Map;

public interface ArchivesDao {
	
	// 获取个人档案ID
	List<Map<String,Object>> getArchives(Map<String, Object> param);
	
	// 获取毒素档案
	String getToxinReport(Long toxinId);
	
	// 获取客户心书检测
	Map<String,Object> getHeartReport(Long heartId);
	
	Map<String, Object> getUrl(Map<String, Object> param);
	
	// 获取血液检测信息
	Map<String, Object> getPhysicalBloodInfos(Long bloodId);
	
	// 获取超声检测信息
	Map<String, Object> getPhysicalUltrasoundInfos(Long ultrasoundId);
	
	// 获取尿液检测信息
	Map<String, Object> getPhysicalUrineInfos(Long urineId);
	
	// 获取太空舱数据
	Map<String, Object> getSpacecapsuleInfos(Long spacecapsuleId);
	
	// 获取综合报告
	String getComprehensiveInfos(Long comprehensiveId);
    
}
