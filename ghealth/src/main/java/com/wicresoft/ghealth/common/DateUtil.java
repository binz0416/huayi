package com.wicresoft.ghealth.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 日期格式化并转换为字符串
	 * 
	 * @param inputDate
	 * @param formatStr
	 * @return
	 */
	static public String getDateString(Date inputDate, String formatStr) {
		if (formatStr != null)
			format.applyPattern(formatStr);
		if (inputDate != null)
			return format.format(inputDate);
		return "";

	}

	/**
	 * 字符串转换为日期格式
	 * 
	 * @param inputDate
	 * @param formatStr
	 * @return
	 */
	static public Date getStringToDate(String str) {
		Date date;
		try {
			date = format.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}