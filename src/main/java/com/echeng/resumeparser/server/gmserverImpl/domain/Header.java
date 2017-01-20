package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.Map;

import lombok.Data;

@Data
public class Header {
	private Map<String, String> headerMap;
	
	public Boolean isValid(){
		return (headerMap.size() > 0) ;
	}
}
