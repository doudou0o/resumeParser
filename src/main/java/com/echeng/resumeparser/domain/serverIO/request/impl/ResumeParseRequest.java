package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.request.IRequest;

import lombok.Data;

/*
 * every request must implement two static functions
 * for check request input ( Map<String, Object> P key value pair )
 */
public class ResumeParseRequest implements IRequest {
	
	private static final String MNAME = "resume_parse";
	
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
		return null;
	}
	
	@Override
	public Boolean isValid(){
		return (null == getReqErrInfo());
	}

	@Override
	public String getReqErrInfo() {
		if (null == groupName)
			return "no parameter named 'group_name' in request";
		if (null == fileName)
			return "no parameter named 'file_name' in request";
		return null;
	}
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