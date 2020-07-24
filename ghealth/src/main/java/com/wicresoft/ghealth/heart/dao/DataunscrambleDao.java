package com.wicresoft.ghealth.heart.dao;

import java.util.Map;

import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;

public interface DataunscrambleDao {

	//原始数据
	// 心书
	Map<String, Object> getHeartInfos(Map<String, Object> condition);
	
	// 心电数据文件地址
	Map<String, Object> getUrl(Map<String, Object> condition);
	
	// 超声检测
	Map<String, Object> getPhysicalUltrasoundInfos(Map<String, Object> condition);
	
	// 血液检测
	Map<String, Object> getPhysicalBloodInfos(Map<String, Object> condition);
	
	// 尿液检测
	Map<String, Object> getPhysicalUrineInfos(Map<String, Object> condition);
	
	// 太空舱
	Map<String, Object> getSpacecapsuleInfos(Map<String, Object> condition);
    
    // 业务数据
	// 档案
	Map<String, Object> getArchiveInfos(String customerId);
	
	// 新规档案详细
	void insertArchivesDetail(ArchivesDetailDto detailDto);
	
	// 更新档案详细
	void updateArchivesDetail(ArchivesDetailDto detailDto);
	
}
