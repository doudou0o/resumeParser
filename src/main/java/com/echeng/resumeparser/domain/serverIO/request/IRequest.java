package com.echeng.resumeparser.domain.serverIO.request;


public interface IRequest {
	//public static Boolean isMethodMatch(String m)
	//public static String getRequestMatchInfo(Map<String,Object> reqDicP)
	public Boolean isValid();
	public String getReqErrInfo();
}
