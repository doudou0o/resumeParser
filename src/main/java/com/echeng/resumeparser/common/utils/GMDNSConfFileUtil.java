package com.echeng.resumeparser.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GMDNSConfFileUtil {
	private static final Logger logger = LoggerFactory.getLogger(GMDNSConfFileUtil.class);
	
	public static String gmConfFile = "/opt/wwwroot/conf/gm.conf";
	public static Map<String,Map<String,List<String>>> gmConfMap;
	
	public static List<String> getAllHosts(){
		Map<String,Map<String,List<String>>> f_gmConfMap = parseGMConfFile();
		
		List<String> Allhosts = new ArrayList<String>();
		if ( null == f_gmConfMap ) return Allhosts;
		
		for (Map<String,List<String>> work : f_gmConfMap.values()){
			List<String> hosts = work.getOrDefault("host", null);
			if (null != hosts)
				Allhosts.addAll(hosts);
		}
		return Allhosts;
	}

	/**
	 * 返回整个文件的Map形式
	 * map<String:workerName, List<String:Host>>
	 * @return
	 */
	public static Map<String,Map<String,List<String>>> getAll(){
		return parseGMConfFile();
	}
	
	public static List<String> getWorkHost(String workerName){
		Map<String,Map<String,List<String>>> f_gmConfMap = parseGMConfFile();
		if ( null == f_gmConfMap || !f_gmConfMap.containsKey(workerName) )
			return null;
		return f_gmConfMap.get(workerName).getOrDefault("host", null);
	}
	
	private static Map<String,Map<String,List<String>>> parseGMConfFile(){
		if (null != gmConfMap)
			return gmConfMap;
		
		try {
			Gson gson =  new Gson();
			String gmConfStr = FileUtils.readFileToString(new File(gmConfFile));
			gmConfMap = gson.fromJson(gmConfStr, new TypeToken<Map<String,Map<String,List<String>>>>(){}.getType());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return gmConfMap;
	}

}
