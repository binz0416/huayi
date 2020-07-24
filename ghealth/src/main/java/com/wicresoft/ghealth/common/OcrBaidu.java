package com.wicresoft.ghealth.common;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class OcrBaidu {


	static public JSONObject reportJson(String file) {
		
		String appId = Utils.getOcrKey("APP_ID");
		String apiKey = Utils.getOcrKey("API_KEY");
		String secretKey = Utils.getOcrKey("SECRET_KEY");
		String template = Utils.getOcrKey("TEMPLATE_SIGN");
		AipOcr client = new AipOcr(appId,apiKey, secretKey);

		
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		
	    
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("detect_direction", "true");
  
	    String image = file;
//	    JSONObject res = client.receipt(image, options);
	    JSONObject res = client.custom(image, template, options);
	    return res;
		
	}
}
