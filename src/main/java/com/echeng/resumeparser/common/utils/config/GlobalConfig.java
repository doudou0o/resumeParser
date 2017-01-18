package com.echeng.resumeparser.common.utils.config;

import java.util.HashMap;
import java.util.Map;

import com.echeng.resumeparser.common.utils.config.Impl.ConfigUnit;

public class GlobalConfig {
	private static Map<String, ALConfig> fileProperties = new HashMap<>();
	public static ALConfig getInstanceFromFile(String fileName) {
		if (fileProperties.containsKey(fileName)) {
			return fileProperties.get(fileName);
		}
		
		ConfigUnit tmpConfig = new ConfigUnit();
		tmpConfig.loadProperties(fileName);
		
		fileProperties.putIfAbsent(fileName, tmpConfig);
		return fileProperties.get(fileName);
	}
}
