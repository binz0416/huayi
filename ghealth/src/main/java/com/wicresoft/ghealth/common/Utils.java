package com.wicresoft.ghealth.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.auth0.jwt.internal.org.bouncycastle.jcajce.provider.symmetric.Threefish;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.itextpdf.text.Font;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.wicresoft.ghealth.common.dto.UserInfo;

import net.sf.cglib.beans.BeanMap;
import net.sf.json.JSONArray;

public class Utils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	static {
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		// 处理时间格式
		DateConverter dateConverter = new DateConverter();
		// 设置日期格式
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd" });
		// 注册格式
		ConvertUtils.register(dateConverter, Date.class);
	}

	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 * @throws BusinessException 
	 * @throws  
	 */
	public static String getMD5(String str) throws BusinessException {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 隶｡邂洋d5蜃ｽ謨ｰ
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String md5 = new BigInteger(1, md.digest()).toString(16);
			// BigInteger会把0省略掉，需补全至32位
			return fillMD5(md5);
		} catch (Exception e) {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			throw new BusinessException(act.getMessage("E0033", null, Locale.getDefault()));
		}
	}

	public static String fillMD5(String md5) {
		return md5.length() == 32 ? md5 : fillMD5("0" + md5);
	}

	/**
	 * 获取随机数，用于生成唯一的主键值，生成规则：yyyyMMddHHmmssSSS+5位随机数=22位的数字字符 2015 0519 0233
	 * 1089 6457 15
	 */
	public static String getRandomNum() {
		Random rand = new Random();
		// 随机生成5位数，不足则在前面默认补0
		String temp = "0000" + rand.nextInt(99999);
		temp = temp.substring(temp.length() - 5);
		return DateUtil.getDateString(new Date(), "yyyyMMddHHmmssSSS") + temp;
	}

	/**
	 * 日期格式化并转换为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param inputDate
	 * @param formatStr
	 * @return
	 */
	public static String dateTimeToStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	/**
	 * String格式化并转换为日历格式
	 * 
	 * @param time
	 * @return
	 * @throws BusinessException 
	 * @throws  
	 */
	public static Calendar getStringToCalendar(String time) throws BusinessException {
		Calendar calendar = Calendar.getInstance();
		// 设置星期一为每个星期的第一天
		calendar.setFirstDayOfWeek(CommonConst.CALENDAR_FIRSTDAYOFWEEK);
		Date date = null;
		if (time != null && time != "") {
			try {
				date = sdf.parse(time);
				calendar.setTime(date);
				return calendar;
			} catch (ParseException e) {
				ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
				throw new BusinessException(act.getMessage("E0032", null, Locale.getDefault()));
			}
		} else {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			throw new BusinessException(act.getMessage("E0032", null, Locale.getDefault()));
		}
	}

	/**
	 * 日期转化成日历格式
	 * 
	 * @param time
	 * @return
	 */
	public static Calendar getCalendar(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(CommonConst.CALENDAR_FIRSTDAYOFWEEK);
		calendar.setTime(time);
		return calendar;
	}

	/**
	 * 日期格式化并转换为yyyyMMdd
	 * 
	 * @param time
	 * @return
	 */
	public static String getDateToYMD(Date time) {
		Calendar calendar = getCalendar(time);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获得选择日期当月的第一天
	 * 
	 * @param time
	 * @return
	 */
	public static String getFirstDateTime(Date time) {
		Calendar firstDate = getCalendar(time);
		firstDate.set(Calendar.DATE, 1);
		return sdf.format(firstDate.getTime());
	}

	/**
	 * 获得选择日期当月的最后一天
	 * 
	 * @param time
	 * @return
	 */
	public static String getLastDateTime(Date time) {
		Calendar lastDate = getCalendar(time);
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH, 1);
		lastDate.add(Calendar.DATE, -1);
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得选择日期当前周的第一天
	 * 
	 * @param time
	 * @return
	 */
	public static String getFirstOfWeek(Date time) {
		Calendar firstDate = getCalendarByDate(time);
		firstDate.set(Calendar.DAY_OF_WEEK, CommonConst.CALENDAR_FIRSTDAYOFWEEK);
		return sdf.format(firstDate.getTime());
	}

	/**
	 * 获得选择日期当前周的最后一天
	 * 
	 * @param time
	 * @return
	 */
	public static String getLastOfWeek(Date time) {
		Calendar lastDate = getCalendarByDate(time);
		lastDate.set(Calendar.DAY_OF_WEEK, CommonConst.CALENDAR_LASTDAYOFWEEK);
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得选择日期的中周的日历对象
	 * 
	 * @param time
	 * @return
	 */
	private static Calendar getCalendarByDate(Date time) {
		Calendar calendar = getCalendar(time);
		if (calendar.get(Calendar.WEEK_OF_YEAR) == 1 && calendar.get(Calendar.MONTH) == 11) {
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
		}
		int typeNum = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.set(Calendar.WEEK_OF_YEAR, typeNum);
		return calendar;
	}

	/**
	 * date2比date1多的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long calcDayOffset(Calendar startCal, Calendar endCal) {
		long day1 = startCal.get(Calendar.DAY_OF_YEAR);
		long day2 = endCal.get(Calendar.DAY_OF_YEAR);

		int year1 = startCal.get(Calendar.YEAR);
		int year2 = endCal.get(Calendar.YEAR);
		if (year1 != year2) {
			// 不同年
			long timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					// 闰年
					timeDistance += 366;
				} else {
					// 不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1) + 1;
		} else {
			// 同一年
			return day2 - day1 + 1;
		}
	}

	/**
	 * date2比date1多的周数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long calcWeekOffset(Calendar startCal, Calendar endCal) {
		long weeks = 0;

		long startWeek = startCal.get(Calendar.WEEK_OF_YEAR);
		long endWeek = endCal.get(Calendar.WEEK_OF_YEAR);

		int year1 = startCal.get(Calendar.YEAR);
		int year2 = endCal.get(Calendar.YEAR);
		if (year1 != year2) {
			// 不同年
			weeks = (year2 - year1) * 52 + (endWeek - startWeek) + 1;
		} else {
			// 同一年
			if (endWeek == 1) {
				weeks = 52 + (endWeek - startWeek) + 1;
			} else {
				weeks = endWeek - startWeek + 1;
			}
		}
		return weeks;
	}

	/**
	 * 获得第二天12点的时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date getSecondDay(Date date) {
		Calendar calendar = getCalendar(date);
		// GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date getLongToDate(Long time, String fstr) throws BusinessException {
		SimpleDateFormat format = new SimpleDateFormat(fstr);
		Date date;
		try {
			date = format.parse(format.format(time));
			return date;
		} catch (Exception e) {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			throw new BusinessException(act.getMessage("E0032", null, Locale.getDefault()));
		}
	}

	public static Date getStringToDate(String str, String fstr) throws BusinessException {
		SimpleDateFormat format = new SimpleDateFormat(fstr);
		Date date;
		try {
			date = format.parse(str);
			return date;
		} catch (Exception e) {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			throw new BusinessException(act.getMessage("E0032", null, Locale.getDefault()));
		}
	}

	/**
	 * 返回实际查找页数
	 *
	 * @param dateCnt
	 * @param curPage
	 * @return
	 */
	public static int findSearchPage(int dateCnt, int curPage) {
		int totalPage;
		int searchPage;
		if (dateCnt % CommonConst.PAGE_SIZE == 0) {
			totalPage = dateCnt / CommonConst.PAGE_SIZE;
		} else {
			totalPage = dateCnt / CommonConst.PAGE_SIZE + 1;
		}
		if (totalPage < curPage) {
			searchPage = totalPage - 1;
		} else {
			searchPage = curPage - 1;
		}
		return searchPage;
	}

	public static ResponseEntity<byte[]> download(String fileName) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			if (StringUtils.isBlank(fileName)) {
				throw new BusinessException(act.getMessage("E0010", null, Locale.getDefault()));
			}
			String path = CommonConst.UPLOAD_FOLDER_PATH + fileName;
			if (StringUtils.startsWith(fileName, CommonConst.TMP_FILE_NAME)) {
				path = CommonConst.TMP_FOLDER_PATH + fileName;
				// 正式下载文件路径
//				path = getPathSet("fileDownloadPath") + fileName;
				// 测试下载文件路径
//				path = getPathSet("fileDownloadPathTest") + fileName;
			}
			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
					HttpStatus.OK);
			return ret;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0023", null, Locale.getDefault()));
		}
	}

	public static Map<String, Object> upload(Map<String, Object> upload) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		// Response rslt = new Response();
		try {
			Map<String, Object> ret = new HashMap<String, Object>();
			CommonsMultipartFile file = (CommonsMultipartFile) upload.get(CommonConst.KEY_UPLOAD_FILE);
			String url = upload.get("url").toString();
			// 判断文件是否存在
			if (!file.isEmpty()) {
				File saveDirectory = new File(CommonConst.TMP_FOLDER_PATH);
				// 正式下载文件路径
//				File saveDirectory = new File(getPathSet("fileDownloadPath"));
				// 测试临时文件路径
//				File saveDirectory = new File(getPathSet("fileDownloadPathTest"));
				if (!saveDirectory.exists()) {
					saveDirectory.mkdirs();
				}
				String fileName = file.getOriginalFilename();
				String fileExtension = FilenameUtils.getExtension(fileName);
				String tmpname = CommonConst.TMP_FILE_NAME + "_" + Utils.getRandomNum() + "." + fileExtension;
				file.transferTo(new File(saveDirectory, tmpname));
				ret.put(CommonConst.KEY_FILE_LINK, tmpname);
//				ret.put(CommonConst.KEY_FILE_LINK, url + tmpname);
				// rslt.success(ret);
			} else {
				throw new BusinessException(act.getMessage("E0009", null, Locale.getDefault()));
			}
			return ret;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0019", null, Locale.getDefault()));
		}
	}
	
	public static Response reportUpload(Map<String, Object> upload)
			throws IllegalStateException, IOException, BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> report = new HashMap<String, Object>();
		CommonsMultipartFile file = (CommonsMultipartFile) upload.get(CommonConst.KEY_UPLOAD_FILE);

		if (!file.isEmpty()) {
			File saveDirectory = new File(CommonConst.TMP_FOLDER_PATH);
			if (!saveDirectory.exists()) {
				saveDirectory.mkdirs();
			}
			String fileName = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(fileName);
			String tmpname = CommonConst.TMP_FILE_NAME + "_" + Utils.getRandomNum() + "." + fileExtension;
			file.transferTo(new File(saveDirectory, tmpname));
			
			if (StringUtils.isBlank(tmpname)) {
				throw new BusinessException(act.getMessage("E0010", null, Locale.getDefault()));
			}
			String path = CommonConst.TMP_FOLDER_PATH + tmpname;
			JSONObject reportJson = OcrBaidu.reportJson(path);
			
			JSONObject data = reportJson.getJSONObject("data");
			
			org.json.JSONArray dataRet = data.getJSONArray("ret");

			for(int i=0;i<dataRet.length();i++){
				JSONObject item = dataRet.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				if("inspectionDate".equals(item.get("word_name").toString())){
					ret.put("inspectionTime", item.get("word").toString());
				}else if("inspectArea".equals(item.get("word_name").toString())){
					ret.put("checkpoint", item.get("word").toString());
				}else if("diagnose".equals(item.get("word_name").toString())){
					ret.put("clinicalDiagnosis", item.get("word").toString());
				}else if("result".equals(item.get("word_name").toString())){
					String result = item.get("word").toString();
					String[] aa = result.split("超声提示");
					if(aa.length>1){
						ret.put("ulDescription", aa[0].replace("超声描述",""));
						ret.put("ulTip",aa[1]);
					}
				}
			}
			report.put("report",ret);	
			rslt.success(report);
		} else {
			throw new BusinessException(act.getMessage("E0009", null, Locale.getDefault()));
		}
		return rslt;
	}


	public static void moveFile(String fileName, String id) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			if (StringUtils.isBlank(fileName)) {
				throw new BusinessException(act.getMessage("E0010", null, Locale.getDefault()));
			}
			if (StringUtils.isBlank(id)) {
				throw new BusinessException(act.getMessage("E0011", null, Locale.getDefault()));
			}
			if (StringUtils.startsWith(fileName, CommonConst.TMP_FILE_NAME)) {
				File saveDirectory = new File(CommonConst.UPLOAD_FOLDER_PATH);
				if (!saveDirectory.exists()) {
					saveDirectory.mkdirs();
				}
				File file = new File(CommonConst.TMP_FOLDER_PATH + fileName);
				fileName = StringUtils.replace(fileName, CommonConst.TMP_FILE_NAME, id);
				file.renameTo(new File(saveDirectory, fileName));
			}
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0020", null, Locale.getDefault()));
		}
	}

	public static void mergeDoc(XWPFDocument srcDocument, XWPFDocument appendDocument) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			CTBody src = srcDocument.getDocument().getBody();
			CTBody append = appendDocument.getDocument().getBody();
			XmlOptions optionsOuter = new XmlOptions();
			optionsOuter.setSaveOuter();
			String appendString = append.xmlText(optionsOuter);
			String srcString = src.xmlText();
			String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
			String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
			String sufix = srcString.substring(srcString.lastIndexOf("<"));
			String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
			CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
			src.set(makeBody);
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0021", null, Locale.getDefault()));
		}
	}

	/**
	 * 将word文档， 转换成pdf 宋体：STSong-Light
	 *
	 * @param fontParam1
	 *            可以字体的路径，也可以是itextasian-1.5.2.jar提供的字体，比如宋体"STSong-Light"
	 * @param fontParam2
	 *            和fontParam2对应，fontParam1为路径时，fontParam2=BaseFont.IDENTITY_H，
	 *            为itextasian-1.5.2.jar提供的字体时，fontParam2="UniGB-UCS2-H"
	 * @param tmp
	 *            源为word文档， 必须为docx文档
	 * @param target
	 *            目标输出
	 */
	public static void wordConverterToPdf(XWPFDocument doc, String target) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			OutputStream targetStream = null;
			String uid = Utils.getRandomNum();
			String tmp = CommonConst.TMP_FOLDER_PATH + uid + ".doc";
			targetStream = new FileOutputStream(tmp);

			doc.write(targetStream);
			IOUtils.closeQuietly(targetStream);
			office2PDF(tmp, target);
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0022", null, Locale.getDefault()));
		}
	}

	/**
	 * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为
	 * http://www.openoffice.org/
	 * 
	 * <pre>
	 *   
	 * 方法示例:  
	 * String sourcePath = "F:\\office\\source.doc";  
	 * String destFile = "F:\\pdf\\dest.pdf";  
	 * Converter.office2PDF(sourcePath, destFile);
	 * </pre>
	 * 
	 * @param sourceFile
	 *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,
	 *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
	 * @param destFile
	 *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
	 * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,
	 *         则表示操作成功; 返回1, 则表示转换失败
	 */
	private static int office2PDF(String sourceFile, String destFile) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			File inputFile = new File(sourceFile);
			if (!inputFile.exists()) {
				return -1;// 找不到源文件, 则返回-1
			}

			// 如果目标路径不存在, 则新建该路径
			File outputFile = new File(destFile);
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			// connect to an OpenOffice.org instance running on port 8100
			OpenOfficeConnection connection = new SocketOpenOfficeConnection("172.18.130.247", 8100);
			connection.connect();

			// convert
			DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);

			converter.convert(inputFile, outputFile);

			// close the connection
			connection.disconnect();

			return 0;
		} catch (ConnectException e) {
			throw new BusinessException(act.getMessage("E0022", null, Locale.getDefault()));
		}
	}

	public static ResponseEntity<byte[]> getReportFile(Map<String, Object> report) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();

		try {
			String uid = (String) report.get("uid");
			String reportFileName = uid + ".pdf";

			File file = new File(CommonConst.REPORT_FOLDER_PATH + reportFileName);
			if (!file.exists()) {
				throw new BusinessException(act.getMessage("E7001", null, Locale.getDefault()));
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", reportFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
					HttpStatus.OK);
			return ret;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0023", null, Locale.getDefault()));
		}
	}

	/**
	 * 将对象装换为map
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key + "", beanMap.get(key));
			}
		}
		return map;
	}

	/**
	 * 将map装换为javabean对象
	 * 
	 * @param map
	 * @param bean
	 * @return
	 * @throws BusinessException
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			BeanUtils.populate(bean, map);
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0024", null, Locale.getDefault()));
		}
		return bean;
	}

	/**
	 * 将List<T>转换为List<Map<String, Object>>
	 * 
	 * @param objList
	 * @return
	 */
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			List<Map<String, Object>> list = Lists.newArrayList();
			if (objList != null && objList.size() > 0) {
				Map<String, Object> map = null;
				T bean = null;
				for (int i = 0, size = objList.size(); i < size; i++) {
					bean = objList.get(i);
					map = beanToMap(bean);
					list.add(map);
				}
			}
			return list;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0024", null, Locale.getDefault()));
		}
	}

	/**
	 * 将List<Map<String,Object>>转换为List<T>
	 * 
	 * @param maps
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			List<T> list = Lists.newArrayList();
			if (maps != null && maps.size() > 0) {
				Map<String, Object> map = null;
				T bean = null;
				for (int i = 0, size = maps.size(); i < size; i++) {
					map = maps.get(i);
					bean = clazz.newInstance();
					mapToBean(map, bean);
					list.add(bean);
				}
			}
			return list;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0024", null, Locale.getDefault()));
		}
	}

	public static Map<String, Object> formatErrors(BindingResult result) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		List<FieldError> errors = result.getFieldErrors();
		Map<String, Object> errMap = new HashMap<String, Object>();
		for (FieldError error : errors) {
			errMap.put(error.getField(), act.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
		}
		return errMap;
	}
	
	public static Map<String, Object> formatEPCustomErrors(BindingResult result) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		List<FieldError> errors = result.getFieldErrors();
		Map<String, Object> errMap = new HashMap<String, Object>();
		for (FieldError error : errors) {
			String para = error.getField().split("\\.")[error.getField().split("\\.").length - 1];
			errMap.put(para, act.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
		}
		return errMap;
	}

	// TODO:
	public static String generateUniqueString() throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			String miliseconds = Calendar.getInstance().get(Calendar.MILLISECOND) + "";
			MessageDigest d = null;
			d = java.security.MessageDigest.getInstance("SHA-1");
			d.reset();
			d.update(miliseconds.getBytes());
			return bytesToHex(d.digest());
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0025", null, Locale.getDefault()));
		}
	}

	public static String bytesToHex(byte[] in) {
		StringBuilder builder = new StringBuilder();
		for (byte b : in) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	public static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public static byte[] hmac(byte[] key, String data) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
			sha256_HMAC.init(secret_key);

			return sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0025", null, Locale.getDefault()));
		}
	}

	public static String hmac_hex(byte[] key, String data) throws BusinessException {
		return new String(Hex.encodeHex(hmac(key, data)));
	}

	public static Map<String, Object> putUserInfoToList(HttpServletRequest request, Map<String, Object> list) {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put(CommonConst.Login_User, loginUser);
		list.put(CommonConst.Organization_Id, loginUser.getOrganizationId());

		return list;
	}

	public static List<Integer> ecgRead(String downloadURL, String uuid) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		try {
			
			String ecgFilePath = CommonConst.EEG_TEMP + File.separatorChar + uuid + File.separatorChar + CommonConst.EEG_FILE_NAME;
			File ecgFile = new File(ecgFilePath);
			if (!ecgFile.exists()) {
				ecgFilePath = "";
				File zipFile = HttpRequestUtil.downloadFile(downloadURL, CommonConst.EEG_TEMP);
				List<String> filelist = ZipUtil.unzip(zipFile, CommonConst.EEG_TEMP + File.separatorChar + uuid);
				for (String filename : filelist) {
					if (StringUtils.endsWith(filename, ".ecg")) {
						ecgFilePath = filename;
						break;
					}
				}
			}
			
			List<Integer> yList = new ArrayList<Integer>();
			InputStream is = new FileInputStream(ecgFilePath);
			int length = is.available();
			byte bt[] = new byte[length];
			is.read(bt);
			is.close();
			int line = 0;
			StringBuilder buf = new StringBuilder();
			String highCode = "";
			String lowCode = "";
			for (byte d : bt) {
				if (line % 16 == 0) {
					buf.append(String.format("", line));
				}
				buf.append(String.format("%02x", d));
				if (line % 2 == 0) {
					highCode = String.format("%02x", d);
				} else {
					lowCode = String.format("%02x", d);
					if (line >= 1500) {
						// tempList.get(i) - 2000) / 4
						yList.add(Integer.parseInt(lowCode + highCode, 16) / 10);
					}
				}
				line++;
				if (yList.size() == 500 * 63) {
					break;
				}
			}
			return yList;
		} catch (Exception e) {
			throw new BusinessException(act.getMessage("E0027", null, Locale.getDefault()));
		}
	}

	/**
	 * 获取列名
	 *
	 * @param flg
	 *            页面标记
	 * @return
	 */
	public static JSONArray getColumns(String flg) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		String columns = act.getMessage(flg, null, Locale.getDefault());
		JSONArray jsons = JSONArray.fromObject(columns);
		return jsons;
	}
	
	/**
	 * 获取图例信息
	 * @param flg
	 * @return
	 */
	public static String[] getArray(String flg) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		String str = act.getMessage(flg, null, Locale.getDefault());
		String[] strArray = str.split(",");
		return strArray;
	}
	
	public static String getOcrKey(String flg) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		String key = act.getMessage(flg, null, Locale.getDefault());
		return key;
	}
	public static String getPathSet(String flg) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		String key = act.getMessage(flg, null, Locale.getDefault());
		return key;
	}
	
	/**
	 * 判断对象属性是否都为null
	 *
	 * @param obj
	 *            对象
	 * @return
	 */
	public static boolean checkObjFieldIsNull(Object obj) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
	    boolean flag = true;
	    for(Field f : obj.getClass().getDeclaredFields()){
	        f.setAccessible(true);
	        try {
				if(f.get(obj) != null){
				    flag = false;
				    return flag;
				}
			}  catch (IllegalAccessException e) {
				throw new BusinessException(act.getMessage("E0034", null, Locale.getDefault()));
			}
	    }
	    return flag;
	}
	
	
	public static ElementList parseToElementList(String html, String css) throws BusinessException {
        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        if (css != null) {
            CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(css.getBytes()));
            cssResolver.addCss(cssFile);
        }

        // HTML
        MyFontsProvider fontProvider = new MyFontsProvider();
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.autoBookmark(false);

        // Pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, end);
        CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, htmlPipeline);

        // XML Worker
        XMLWorker worker = new XMLWorker(cssPipeline, true);
        XMLParser p = new XMLParser(worker);
        html = html.replace("<hr>", "").replace("<img>", "").replace("<param>", "")
                .replace("<link>", "");
        //html = "<div style='color:green;font-size:20px;'>菴�螂ｽ荳也阜�ｼ”ello world !</div>";


        try {
			p.parse(new ByteArrayInputStream(html.getBytes()));
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return elements;
    }
	public static class MyFontsProvider extends XMLWorkerFontProvider {
        public MyFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {

            String fntname = fontname;
            if (fntname == null) {
                fntname = "螳倶ｽ�";
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

}
