package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.HashMap;
import java.util.Map;

import com.echeng.resumeparser.common.utils.JsonUtil;

import lombok.Data;

@Data
public class Header {
	private Map<String, String> headerMap;
	
	public Header(){
		headerMap = new HashMap<String, String>();
	}
	
	public Boolean isValid(){
		return (headerMap.size() > 0) ;
	}
	
	@Override
	public String toString(){
		return JsonUtil.toJson(headerMap);
	}
}
