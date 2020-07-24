package com.wicresoft.ghealth.catering.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.wicresoft.ghealth.auto.mapper.TCateringMapper;
import com.wicresoft.ghealth.auto.mapper.TCustomerMapper;
import com.wicresoft.ghealth.auto.mapper.TSetMealMapper;
import com.wicresoft.ghealth.auto.pojo.TCatering;
import com.wicresoft.ghealth.catering.dao.CateringDao;
import com.wicresoft.ghealth.catering.service.ISetMealService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.CustomerCheckinInfo;
import com.wicresoft.ghealth.common.dto.CustomerInfo;

@Service("setMealService")
public class SetMealService implements ISetMealService {

	@Resource
	private CateringDao cateringDao;
	
	@Resource
	private TCustomerMapper customerMapper;

	@Resource
	private TCateringMapper cateringMapper;

	@Resource
	private TSetMealMapper setMealMapper;
	
	@Resource
	private CommonDao commonDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response camera(Map<String, Object> camera) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
//		String cameraNo = (String) camera.get("cameraNo");
//		int faceId = Integer.parseInt((String) camera.get("faceId"));
//
//		Map<String, Object> param = new HashMap<String, Object>();
//		param = new HashMap<String, Object>();
//		param.put("faceId", faceId);
//		List<TCustomer> customers = cateringDao.selectByFaceid(param);
//
//		String customerId = null;
//		String name = "";
//		if (customers != null && !customers.isEmpty()) {
//			customerId = customers.get(0).getCustomerId();
//			//name = customers.get(0).getName();
//			param = new HashMap<String, Object>();
//			param.put("customerId", customerId);
//			List<TSetMeal> setMeals = cateringDao.selectByUse(param);
//
//			if (setMeals != null && !setMeals.isEmpty()) {
//				TCatering catering = new TCatering();
//				catering.setCustomerId(customerId);
//				catering.setCameraNo(cameraNo);
//				//catering.setName(name);
//				catering.setSetMealId(setMeals.get(0).getSetMealId());
//				catering.setCaptureTime(new Date(Long.parseLong((String) camera.get("captureTime"))));
//				//catering.setFaceId(faceId);
//				cateringMapper.insert(catering);
//				rslt.success();
//			} else {
//				rslt.failure(act.getMessage("E0014", null, Locale.getDefault()));
//			}
//		} else {
//			rslt.failure(act.getMessage("E0013", null, Locale.getDefault()));
//		}
		return rslt;
	}

	@Override
	public Response refresh(Map<String, Object> list) {
		Response rslt = new Response();
		/*List<CateringModel> cateringModels = new ArrayList<CateringModel>();
		List<TCatering> caterings = cateringDao.selectAll();
		for (TCatering catering : caterings) {
			CateringModel cateringModel = new CateringModel();
			BeanUtils.copyProperties(catering, cateringModel);
			TSetMeal setMeal = setMealMapper.selectByPrimaryKey(catering.getSetMealId());
			StringBuffer mealbuf = new StringBuffer();
			String[] mealstrs = StringUtils.split(setMeal.getMeals(), ",");
			for (String meal : mealstrs) {
				String[] mealinfo = StringUtils.split(meal, "|");
				mealbuf.append(mealinfo[0]);
				mealbuf.append(" ");
			}
			cateringModel.setMeal(mealbuf.toString());
			cateringModels.add(cateringModel);
		}
		rslt.success(cateringModels);*/
		return rslt;
	}

	@Override
	public Response getPrintData(Map<String, Object> list) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		/*Long setMealId = Long.parseLong(String.valueOf(list.get("setMealId")));

		PrintInfo printInfo = new PrintInfo();
		printInfo.setLogo("/ghealth/image/custome.png");
		printInfo.setCorporateName(act.getMessage("COMP_NAME", null, Locale.getDefault()));
		TSetMeal setMeal = setMealMapper.selectByPrimaryKey(setMealId);
		printInfo.setMealContent(setMeal.getMealcontent());
		printInfo.setNumberNo(String.valueOf(setMealId));
		List<Meals> mealsList = new ArrayList<Meals>();
		String[] mealstrs = StringUtils.split(setMeal.getMeals(), ",");
		for (String meal : mealstrs) {
			String[] mealinfo = StringUtils.split(meal, "|");
			Meals meals = new Meals();
			meals.setMealName(mealinfo[0]);
			meals.setMealNum(mealinfo[1]);
			mealsList.add(meals);
		}
		printInfo.setMealsList(mealsList);
		rslt.success(printInfo);*/
		return rslt;
	}

	@Override
	public Response isPrintOK(Map<String, Object> edit) {
		Response rslt = new Response();
		Long cateringId = Long.parseLong(String.valueOf(edit.get("cateringId")));
		TCatering catering = new TCatering();
		catering.setCateringId(cateringId);
		catering.setValid(1);
		cateringMapper.updateByPrimaryKeySelective(catering);
		rslt.success();
		return rslt;
	}
	
	@Override
	public Response getCustomers(Map<String, Object> param) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		PaginationUtils.initSelectParam(param);
		// 总数据条数
		int totalCount = this.commonDao.countCustomers(param);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<CustomerInfo> customerList = this.commonDao.getCustomerInfos(param);
			for (CustomerInfo customer : customerList) {
				List<CustomerCheckinInfo> checkinInfos = customer.getCheckinInfos();
				if (checkinInfos != null && checkinInfos.size() > 0) {
					customer.setCustomerLastCheckin(checkinInfos.get(0).getCustomerCheckin());
					customer.setCustomerLastCheckinDsp(checkinInfos.get(0).getCustomerCheckinDsp());
				}

			}
			ret.put("checkinInfos", customerList);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_LEFTCUSTOMERS));
		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response getCustomerMeals(Map<String, Object> param) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		
		Map<String, Object> mealsData = this.cateringDao.getCustomerMeals(param);
		if (mealsData != null) {
			ret.put("mealsData", mealsData);
			rslt.success(ret);
		}else {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("E0014", null, Locale.getDefault()));
			rslt.failure(errMap);
		}
		return rslt;
	}

}
