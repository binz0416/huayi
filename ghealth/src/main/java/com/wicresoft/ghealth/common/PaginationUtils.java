package com.wicresoft.ghealth.common;

import java.util.Map;
public class PaginationUtils {
	
	public static int DEFAULT_PAGE_SIZE = 10;
	public static int DEFAULT_PAGE = 0;
	
	/**
	 * 初始化分页检索条件
	 */
	public static void initSelectParam(Map<String, Object> param){
		int page = DEFAULT_PAGE;
		int pageSize = DEFAULT_PAGE_SIZE;
		
		if (param.get("pagesize") != null) {
			pageSize = Integer.parseInt(param.get("pagesize").toString());
		}
		
		if (param.get("currentPage") != null) {
			int currentPage = Integer.parseInt(param.get("currentPage").toString());
			page = ((currentPage - 1) * pageSize);
		}
		param.put("page", page);
		param.put("pageSize", pageSize);
	}
	
}
