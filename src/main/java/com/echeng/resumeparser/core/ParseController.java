package com.echeng.resumeparser.core;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.serverIO.request.Request;
import com.echeng.resumeparser.domain.serverIO.response.Response;

public class ParseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseController.class);
	
	public Response handle(String m, Request request) {
		if (Constant.M_NAMEFILTER.equals(m)){
			
		}
		if (Constant.M_NAMEFILTER.equals(m)){
			
		}
		return null;
	}
}
