package com.echeng.resumeparser.common.log;

import java.io.File;


public class LoadConfig {
	/**
	 * windows and mac，加载 config/log4j.properties
	 * @param fileName
	 * @return
	 */
	public static String getConfigNameForLogSetting(String fileName) {
		String retFileName = fileName;
		String os = System.getProperty("os.name"); 
		
		//开发的时候打印到console中，否则开发的人员比较容易修改上线使用的config/log4j.properties
		if(os.toLowerCase().startsWith("windows") || os.toLowerCase().startsWith("mac")) {
			retFileName += ".dev";
		}
		
		File file = new File(retFileName);
		if (!file.exists()) {
			System.err.printf("file %s is not exist, use config file %s.",retFileName, fileName);
			retFileName = fileName;
		}
		return retFileName;
	}
}
