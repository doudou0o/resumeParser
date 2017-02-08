package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.Map;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;

public class ResumeElementFilterRequest implements IRequest {
	
	private static final String MNAME = Constant.M_NAMEFILTER;

	// must implement
	public static Boolean isMethodMatch(String m){
		return MNAME.equals(m);
	}
	// must implement
	public static String getRequestMatchInfo(Map<String,Object> reqDicP){
		return "coming soon";
	}

	@Override
	public void buildRequest(Map<String, Object> reqDict) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean hasParameter(String param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getParameterValue(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
