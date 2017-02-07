package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.Map;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;

import lombok.Data;

/*
 * every request must implement two static functions
 * for check request input ( Map<String, Object> P key value pair )
 */
public class ResumeParseRequest implements IRequest {
	
	private static final String MNAME = Constant.M_RESUMEPARSE;
	
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