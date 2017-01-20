package com.echeng.resumeparser.domain.serverIO.request.impl;

import com.echeng.resumeparser.domain.serverIO.request.Request;

import lombok.Data;

public class ResumeParseRequest implements Request {
	private String groupName;
	private String fileName;

	private String resumeName;
	private byte[] fileContent;
	
	private Option option;
	
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