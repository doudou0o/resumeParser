package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.request.IRequest;

public class ResumeElementFilterRequest implements IRequest {
	
	private static final String MNAME = "resume_element_filter";

	// must implement
	public static Boolean isMethodMatch(String m){
		return MNAME.equals(m);
	}
	// must implement
	public static String getRequestMatchInfo(Map<String,Object> reqDicP){
		return null;
	}
		
	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReqErrInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
