package com.wicresoft.ghealth.archives.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.wicresoft.ghealth.archives.dao.ArchivesDao;
import com.wicresoft.ghealth.archives.service.IArchivesmanagerService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.CustomerInfo;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.heart.dao.DataunscrambleDao;

@Service("archivesmanagerService")
public class ArchivesmanagerService implements IArchivesmanagerService {

	@Resource
	private DataunscrambleDao dataunscrambleDao;

	@Resource
	private ArchivesDao archivesDao;

	@Resource
	private CommonDao commonDao;

	@Override
	public Response reportCreate(Map<String, Object> report) throws BusinessException {
		Response rslt = new Response();

		String uid = Utils.getRandomNum();
		String productid = String.valueOf(report.get("productid"));

		List<String> productids = new ArrayList<String>(Arrays.asList(StringUtils.split(productid, ',')));
		if (productids.contains("全部")) {
			productids = null;
		}

		String timezones = String.valueOf(report.get("year"));
		String today = Utils.getDateToYMD(new Date());

		String startDate = "";
		String endDate = "";
		if (StringUtils.endsWith(timezones, "01")) {
			startDate = StringUtils.left(timezones, 4) + "0101";
			endDate = StringUtils.left(timezones, 4) + "0630";
		} else {
			startDate = StringUtils.left(timezones, 4) + "0701";
			endDate = StringUtils.left(timezones, 4) + "1231";
		}

		String templetPath = String.valueOf(report.get("templetPath"));
		String templetFile = templetPath + "/Forcast.docx";

		// XWPFDocument tmpdoc = new XWPFDocument(new
		// FileInputStream(templetPath + "/blank.docx"));

		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("name", "测试");
		datas.put("today", today);
		if (StringUtils.endsWith(timezones, "01")) {
			datas.put("time", StringUtils.left(timezones, 4) + "上半年");
			datas.put("m_1", "1");
			datas.put("m_2", "2");
			datas.put("m_3", "3");
			datas.put("m_4", "4");
			datas.put("m_5", "5");
			datas.put("m_6", "6");
		} else {
			datas.put("time", StringUtils.left(timezones, 4) + "下半年");
			datas.put("m_1", "7");
			datas.put("m_2", "8");
			datas.put("m_3", "9");
			datas.put("m_4", "10");
			datas.put("m_5", "11");
			datas.put("m_6", "12");
		}
		datas.put("pic", new PictureRenderData(100, 120, "/tmp/pic.jpg"));
		XWPFTemplate template1 = XWPFTemplate.compile(templetFile).render(datas);

		// Utils.mergeDoc(tmpdoc, template.getXWPFDocument());

		XWPFTemplate template2 = XWPFTemplate.compile(templetFile).render(datas);

		Utils.mergeDoc(template1.getXWPFDocument(), template2.getXWPFDocument());

		File saveDirectory = new File(CommonConst.REPORT_FOLDER_PATH);
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		Utils.wordConverterToPdf(template1.getXWPFDocument(), CommonConst.REPORT_FOLDER_PATH + uid + ".pdf");

		Map<String, Object> ret = new HashMap<String, Object>();
		StringBuffer url = new StringBuffer();
		UserInfo loginUser = (UserInfo) report.get(CommonConst.Login_User);
		String token = String.valueOf(report.get("token"));
		url.append(CommonConst.PDF_FILE_URL);
		url.append("&Authorization=");
		url.append(token);
		url.append("&LoginUser=");
		url.append(loginUser.getUsername());
		url.append("&uid=");
		url.append(uid);
		ret.put("url", url.toString());
		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response getCustomers(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.commonDao.countCustomers(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<CustomerInfo> checkinInfos = this.commonDao.getCustomerInfos(list);
			ret.put("checkinInfos", checkinInfos);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_LEFTCUSTOMERS));
		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response getUserArchives(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = getCustArchiveInfo(list);
		rslt.success(ret);
		return rslt;
	}
	@Override
	public Map<String, Object> getCustArchiveInfo(Map<String, Object> list)throws BusinessException{
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Map<String, Object>> archives = this.archivesDao.getArchives(list);
		if (archives != null && archives.size() > 0) {
			ret.put("advise", archives.get(0).get("advise").toString());
			for (Map<String, Object> archive : archives) {
				String type = archive.get("type").toString();
				Long scId = Long.valueOf(archive.get("scId").toString());
				String detailAdvise = null;
				if (archive.containsKey("detailAdvise")) {
					detailAdvise = archive.get("detailAdvise").toString();
				}
				// 太空舱
				if (CommonConst.ARCHIVES_TYPE_SPACECAPSULE.equals(type)) {
					Map<String, Object> spacecapsuleData = this.archivesDao.getSpacecapsuleInfos(scId);
					if (spacecapsuleData != null) {
						spacecapsuleData.put("detailAdvise", detailAdvise);
						ret.put("spacecapsuleData", spacecapsuleData);
					}
				}
				// 心书
				if (CommonConst.ARCHIVES_TYPE_HEART.equals(type)) {
					Map<String, Object> heartinfo = this.archivesDao.getHeartReport(scId);
					if (heartinfo != null) {
						heartinfo.put("detailAdvise", detailAdvise);
						ret.put("wearableData", heartinfo);
					}
				}
				// 毒素检测档案
				if (CommonConst.ARCHIVES_TYPE_TOXIN.equals(type)) {
					String toxin = this.archivesDao.getToxinReport(scId);
					if (toxin != null) {
						ret.put("toxindetection", toxin);
					}
				}
				// 超声报告
				if (CommonConst.ARCHIVES_TYPE_ULTRASOUND.equals(type)) {
					Map<String, Object> ultrasoundData = this.archivesDao.getPhysicalUltrasoundInfos(scId);
					if (ultrasoundData != null) {
						ultrasoundData.put("detailAdvise", detailAdvise);
						ret.put("ultrasoundData", ultrasoundData);
					}
				}
				// 血检报告
				if (CommonConst.ARCHIVES_TYPE_BLOOD.equals(type)) {
					Map<String, Object> bloodData = this.archivesDao.getPhysicalBloodInfos(scId);
					if (bloodData != null) {
						bloodData.put("detailAdvise", detailAdvise);
						ret.put("bloodData", bloodData);
					}
				}
				// 尿检报告
				if (CommonConst.ARCHIVES_TYPE_URINE.equals(type)) {
					Map<String, Object> urineData = this.archivesDao.getPhysicalUrineInfos(scId);
					if (urineData != null) {
						urineData.put("detailAdvise", detailAdvise);
						ret.put("urineData", urineData);
					}
				}
				// 综合报告档案
				if (CommonConst.ARCHIVES_TYPE_COMPREHENSIVE.equals(type)) {
					String comprehensive = this.archivesDao.getComprehensiveInfos(scId);
					if (comprehensive != null) {
						ret.put("comprehensiveData", comprehensive);
					}
				}
			}
			
		}
		return ret;
	}
	@Override
	public Map<String, Object> getCustArchiveInfoForReport(Map<String, Object> list)throws BusinessException{
		Map<String, Object> all = new HashMap<String, Object>();
		Map<String, Object> userInfo = this.commonDao.getCustomerInfo(list);
		all.putAll(userInfo);
		List<Map<String, Object>> archives = this.archivesDao.getArchives(list);
		if (archives != null && archives.size() > 0) {
			all.put("advise", archives.get(0).get("advise").toString());
			for (Map<String, Object> archive : archives) {
				String type = archive.get("type").toString();
				Long scId = Long.valueOf(archive.get("scId").toString());
				String detailAdvise = null;
				if (archive.containsKey("detailAdvise")) {
					detailAdvise = archive.get("detailAdvise").toString();
				}
				// 超声报告
				if (CommonConst.ARCHIVES_TYPE_ULTRASOUND.equals(type)) {
					Map<String, Object> ultrasoundData = this.archivesDao.getPhysicalUltrasoundInfos(scId);
					if (ultrasoundData != null) {
						ultrasoundData.put("ultrasoundDetailAdvise", detailAdvise);						
						all.putAll(ultrasoundData);
						all.put("BFlag", true);
					}
				}
				// 血检报告
				if (CommonConst.ARCHIVES_TYPE_BLOOD.equals(type)) {
					Map<String, Object> bloodData = this.archivesDao.getPhysicalBloodInfos(scId);
					if (bloodData != null) {
						bloodData.put("bloodDetailAdvise", detailAdvise);
						all.putAll(bloodData);
						all.put("BloodFlag", true);
					}
				}
				// 尿检报告
				if (CommonConst.ARCHIVES_TYPE_URINE.equals(type)) {
					Map<String, Object> urineData = this.archivesDao.getPhysicalUrineInfos(scId);
					if (urineData != null) {
						urineData.put("urineDetailAdvise", detailAdvise);
						all.putAll(urineData);
						all.put("UrineFlag", true);
					}
				}
				// 太空舱
				if (CommonConst.ARCHIVES_TYPE_SPACECAPSULE.equals(type)) {
					Map<String, Object> spacecapsuleData = this.archivesDao.getSpacecapsuleInfos(scId);
					if (spacecapsuleData != null) {
						spacecapsuleData.put("spacecapsuleDetailAdvise", detailAdvise);
						all.putAll(spacecapsuleData);
						all.put("SpaceFlag", true);
					}
				}
				// 心书
				if (CommonConst.ARCHIVES_TYPE_HEART.equals(type)) {
					Map<String, Object> heartinfo = this.archivesDao.getHeartReport(scId);
					if (heartinfo != null) {
						heartinfo.put("heartinfoDetailAdvise", detailAdvise);
						all.putAll(heartinfo);
						all.put("HeartFlag", true);
					}
				}
				// 毒素检测档案
				if (CommonConst.ARCHIVES_TYPE_TOXIN.equals(type)) {
					String toxin = this.archivesDao.getToxinReport(scId);
					if (toxin != null) {
						all.put("toxindetection",toxin);
					}
				}
				// 综合报告档案
				if (CommonConst.ARCHIVES_TYPE_COMPREHENSIVE.equals(type)) {
					String comprehensive = this.archivesDao.getComprehensiveInfos(scId);
					if (comprehensive != null) {
						all.put("comprehensiveData", comprehensive);
					}
				}
			}
			return all;
		}else{
			return null;
		}
		
	}
	@Override
	public Response getEcgData(Map<String, Object> condition) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		condition.put("type", CommonConst.ARCHIVES_TYPE_HEART);
		Map<String, Object> urlMap = this.archivesDao.getUrl(condition);
		if (urlMap != null) {
			List<Integer> ecgData = Utils.ecgRead(urlMap.get("url").toString(), urlMap.get("uuid").toString());
			ret.put("ecgData", ecgData);
			rslt.success(ret);
		}else {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("I0002", null, Locale.getDefault()));
			rslt.failure(errMap);
		}
		rslt.success(ret);
		return rslt;
	}
}
