package com.echeng.resumeparser.domain.serverIO.request;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest;

public class RequestHelper {
	
	private static final Logger logger = LoggerFactory.getLogger("RequestHelper");
	
	private static final String PACKAGENAME = "com.echeng.resumeparser.domain.serverIO.request.impl";
	private static List<Class<IRequest>> reqClses;
	
	public static Boolean checkRequestP(String m, Map<String, Object> reqDict){
		return null == getErrInfoByRequestP( m, reqDict );
			
	}
	
	public static String getErrInfoByRequestP(String m, Map<String, Object> reqDict){
		Boolean M_matched = false;
		for (Class<IRequest> req : RequestHelper.getAllReqClass()){
			try {
				Method isMmatchFunc = req.getMethod("isMethodMatch", String.class);
				Method getMatchInfoFunc = req.getMethod("getRequestMatchInfo", Map.class);
				if( (Boolean) isMmatchFunc.invoke(null, m) ){
					M_matched = true;
					String ans = (String) getMatchInfoFunc.invoke(null, reqDict);
					if (null == ans || "".equals(ans))
						return null;
					else
						return ans;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		if (!M_matched)
			return "no request handle this M: " + m;
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private static List<Class<IRequest>> getAllReqClass(){
		if (null != reqClses)
			return reqClses;
		reqClses = new ArrayList<Class<IRequest>>();

		// Filter .class files.
		String packagePath = RequestHelper.class.getClassLoader().getResource(PACKAGENAME.replace(".", "/")).getFile();
		File[] files = new File(packagePath).listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".class");
		    }
		});

		// Find classes
		for (File file : files) {
		    String className = file.getName().replaceAll(".class$", "");
			try {
				Class<?> cls = Class.forName(PACKAGENAME + "." + className);
				if (IRequest.class.isAssignableFrom(cls)) {
					reqClses.add((Class<IRequest>) cls);
				}
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
			}
		}
		return reqClses;
	}

	public static IRequest buildResumeParseReq(LinkedHashMap<String, Object> reqDict) {
		return new ResumeParseRequest(reqDict);
	}
	
}
