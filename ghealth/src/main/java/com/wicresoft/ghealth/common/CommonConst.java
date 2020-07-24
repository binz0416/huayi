package com.wicresoft.ghealth.common;

import java.util.Calendar;

public class CommonConst {
	/** 存放鉴权信息的Header名称，默认是Authorization */
	public final static String Login_User = "LoginUser";
	/** 用户所在机构代码 */
	public final static String Organization_Id = "organizationId";

	/**
	 * 存放登录用户模型Key的Request Key
	 */
	public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

	/** 存放Log ID的Request Key */
	public static final String CST_LOG_ID = "CST_LOG_ID";
	public static final String KEY_FILE_NAME = "filename";
	public static final String KEY_UPLOAD_FILE = "uploadfile";
	public static final String KEY_FILE_LINK = "link";

	/** 模板文件路径 */
	public static final String TEMPLET_FOLDER_NAME = "templet";

	/** 临时文件路径 */
	public static final String TMP_FILE_NAME = "TMP";
	public static final String TMP_FOLDER_PATH = "/tmp/";
	
	public static final String DOWNLOAD_FOLDER_PATH = "/download";
	
	/** 临时心电数据 */
	public static final String EEG_TEMP = "/ecgtemp";
	public static final String EEG_FILE_NAME = "raw_data.ecg";

	public static final String TOXIN_FILE_NAME = "TOXIN";

	/** 上传文件路径 */
	public static final String UPLOAD_FOLDER_PATH = "/upload/";
	public static final String REPORT_FOLDER_PATH = "/report/";
	public static final String PDF_FILE_URL = "/archivesmanager/listreport?";

	/**
	 * 每页显示行数
	 */
	public static final int PAGE_SIZE = 25;

	/**
	 * 日历每周的开始日和结束日
	 *
	 */
	public static final int CALENDAR_FIRSTDAYOFWEEK = Calendar.SUNDAY; // 星期日
	public static final int CALENDAR_LASTDAYOFWEEK = Calendar.SATURDAY; // 星期六

	/**
	 * 固定字符
	 *
	 */
	public static final String STRING_EMPTY = "";
	public static final String STRING_DEFAULT = "0";
	public static final String STRING_YEAR = "年";
	public static final String STRING_MONTH = "月";
	public static final String STRING_DAY = "日";
	public static final String STRING_WEEK = "周";
	public static final String STRING_UNDERLINE = "-";
	public static final String STRING_MESSAGE_KEY = "messagekey";

	/** 心书 */
	public static final String HEART_BOOK_URL = "http://manage.heartbooktech.com:10086/wc/getReport";
	public static final String HEART_BOOK_KEY = "V2C#h8XtE04RABnc";
	public static final int TYPE_HEART = 1;
	public static final String ECG_URL = "/templet/raw_data.ecg";///ghealth/src/main/webapp/templet/raw_data.ecg

	/** 健康档案类型 */
	public static final String ARCHIVES_TYPE_SPACECAPSULE = "1"; // 太空舱
	public static final String ARCHIVES_TYPE_HEART = "2"; // 心书
	public static final String ARCHIVES_TYPE_TOXIN = "3"; // 毒素检测
	public static final String ARCHIVES_TYPE_ULTRASOUND = "4"; // 超声报告
	public static final String ARCHIVES_TYPE_BLOOD = "5"; // 血检报告
	public static final String ARCHIVES_TYPE_URINE = "6"; // 尿检报告
	public static final String ARCHIVES_TYPE_COMPREHENSIVE = "7"; // 综合报告

	/** 健康档案类型 */
	// 默认删除FLG
	public static final String DEFAULT_DELETE_FLG_0 = "0";
	public static final String DEFAULT_DELETE_FLG_1 = "1";
	// 默认权限组
	public static final Long DEFAULT_DELETE_ROLE_ID = new Long(3);

	/** 用户登入 */
	public static final String DEFAULT_PWD_CHANGED = "1";
	
	/** 页面列flg */
	//客户管理画面显示列
	public static final String PAGE_FLG_CUSTOMMANAGER = "custommanager";
	//客户共通显示列
	public static final String PAGE_FLG_LEFTCUSTOMERS = "leftcustomers";
	//用户管理画面显示列
	public static final String PAGE_FLG_USERCOLUMNS = "usercolumns";
	//权限配置画面显示列
	public static final String PAGE_FLG_AUTHCOLUMNS = "authcolumns";
	//系统配置画面显示列
	public static final String PAGE_FLG_CONFIGCOLUMNS = "configcolumns";
	//企业客户画面显示列
	public static final String PAGE_FLG_EPCUSTOMMANAGER = "epcustommanager";
	//企业客户查看联系人画面显示列
	public static final String PAGE_FLG_CXCONTACTSCOLUMNS = "cxcontactscolumns";
	//联系人画面显示列
	public static final String PAGE_FLG_ALLCONTACTSCOLUMNS = "allcontactscolumns";
	// 客户统计报表列
	public static final String PAGE_CUSTOMTJCOLUMNS = "customtjcolumns";
	// 客户来源统计图例
	public static final String PAGE_ORIGINTL = "origintl";
	// 客户员工人数统计图例	
	public static final String PAGE_NOOFEMPLOYEESTL = "noofemployeestl";
	// 客户营业额统计图例
	public static final String PAGE_ANNUALREVENUETL = "annualrevenuetl";
	// 客户行业统计图例
	public static final String PAGE_INDUSTRYTL = "industrytl";
			

}
