package com.wicresoft.ghealth.heart.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;

public interface IDataunscrambleService {
	
	public Response getCustomers(Map<String, Object> list) throws BusinessException;

	public Response list(@RequestBody Map<String, Object> list);

	public Response init(Map<String, Object> list) throws BusinessException;

	public Response add(Map<String, Object> add) throws BusinessException;

	public Response edit(ArchivesDetailDto detailDto) throws BusinessException;

	public Response dele(Map<String, Object> dele) throws BusinessException;
	
	public Response getEcgData(Map<String, Object> condition) throws BusinessException;
	
}
