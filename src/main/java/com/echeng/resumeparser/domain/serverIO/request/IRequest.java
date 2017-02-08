package com.echeng.resumeparser.domain.serverIO.request;

import java.util.Map;

public interface IRequest {
	//public static Boolean isMethodMatch(String m)
	//public static String getRequestMatchInfo(Map<String,Object> reqDicP)
	public void buildRequest(Map<String, Object> reqDict);
	public Boolean hasParameter(String param);
	public Object getParameterValue(String param);
}
