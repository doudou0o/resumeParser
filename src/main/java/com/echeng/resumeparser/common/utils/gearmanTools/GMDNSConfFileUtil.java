package com.echeng.resumeparser.common.utils.gearmanTools;

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
	public static String gmConfFile_win = "D:/gm.conf"; //Just 4 test in windows

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
			
			File f = new File(gmConfFile_win);
			
			if (!f.exists())
				f = new File(gmConfFile);

			String gmConfStr = FileUtils.readFileToString(f);
			
			gmConfMap = gson.fromJson(gmConfStr, new TypeToken<Map<String,Map<String,List<String>>>>(){}.getType());
		
		} catch (IOException e) {
			logger.error("no gm.conf found(linux:/opt/wwwroot/conf/gm.conf, windows(test):D:/gm.conf)",e);
		}
		
		return gmConfMap;
	}

}
