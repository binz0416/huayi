package com.wicresoft.ghealth.common;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.wicresoft.ghealth.auto.mapper.TActlogMapper;
import com.wicresoft.ghealth.auto.pojo.TActlog;

public class DefaultExceptionHandler implements HandlerExceptionResolver {
	// private static Logger log =
	// LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@Resource
	private TActlogMapper actLogDao;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		ModelAndView mv = new ModelAndView();
		try {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // 设置状态码
			String message = "";
			if (ex instanceof BusinessException) {
				message = ex.getMessage();
			} else {
				message = act.getMessage("E0000", null, Locale.getDefault());
			}
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
			writer.write(message);
			writer.close();
		} catch (Exception e) {
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			Map<String, Object> meta = new HashMap<String, Object>();
			meta.put("message", e.getClass());
			attributes.put("meta", meta);
			view.setAttributesMap(attributes);
			mv.setView(view);

		}

		Long id = (Long) request.getAttribute(CommonConst.CST_LOG_ID);

		if (id != null) {
			TActlog actLog = actLogDao.selectByPrimaryKey(id);
			actLog.setStat("E");
			actLog.setInfo(this.getStackMsg(ex));
			actLogDao.updateByPrimaryKeyWithBLOBs(actLog);
		}

		return mv;
	}

	private String getStackMsg(Exception e) {

		StringBuffer sb = new StringBuffer();
		sb.append(e.getClass().toString() + "\n");
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}

}
