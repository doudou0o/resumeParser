package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;

import lombok.Data;

/*
 * every request must implement two static functions
 * for check request input ( Map<String, Object> P key value pair )
 */
@Data
public class ResumeParseRequest implements IRequest {
	
	private static final String MNAME = Constant.M_RESUMEPARSE;
	
	private Map<String,Object> m_reqDicP;
	
	private String groupName;
	private String fileName;

	private String resumeName;
	private byte[] fileContent;
	
	private Option option;

	// must implement
	public static Boolean isMethodMatch(String m){
		return MNAME.equals(m);
	}
	// must implement
	public static String getRequestMatchInfo(Map<String,Object> reqDicP){
		if (null == reqDicP)
			return "no request parameters";
		if (!reqDicP.containsKey("groupname") || "".equals(reqDicP.get("groupname")))
			return "no 'groupname' in request parameters";
		if (!reqDicP.containsKey("filename") || "".equals(reqDicP.get("filename")))
			return "no 'filename' in request parameters";
		return null;
	}
	
	public ResumeParseRequest(){};
	
	public ResumeParseRequest(LinkedHashMap<String, Object> reqDict) {
		this.buildRequest(reqDict);
	}


	@Override
	public void buildRequest(Map<String, Object> reqDict) {
		this.m_reqDicP = reqDict;
	}
	@Override
	public Boolean hasParameter(String param) {
		return null;
	}
	@Override
	public Object getParameterValue(String param) {
		return null;
	}
	


	@Data
	class Option {
		// run type
		private Integer runType;
		// max time out -- means must return before timeOut is up
		private long timeOut;
		// need filter name or not (default is false)
		private Integer isFilterName;
		// constraint_degree
		private Integer constraint_degree;
	}
}

