package com.echeng.resumeparser.core;

import java.util.LinkedHashMap;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.domain.serverIO.response.Response;

public class ParseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseController.class);
	
	public Response handle(String m, LinkedHashMap<String, Object> reqDict) {
		if (Constant.M_NAMEFILTER.equals(m)){
			
		}
		if (Constant.M_NAMEFILTER.equals(m)){
			
		}
		return null;
	}
}
