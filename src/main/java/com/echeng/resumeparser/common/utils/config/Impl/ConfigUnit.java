package com.echeng.resumeparser.common.utils.config.Impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.echeng.resumeparser.common.utils.EnvironmentUtil;
import com.echeng.resumeparser.common.utils.config.ALConfig;

public class ConfigUnit implements ALConfig {
	Map<String, String> configMap = new HashMap<>();
	private Properties properties = new Properties();
	
	@Override
	public String getString(String key) throws IllegalArgumentException{
		if (properties.containsKey(key)) {
			String tmp = properties.getProperty(key);
			return tmp;
		}
		
		throw new IllegalArgumentException("key["+key+"] is not exist.");
	}
	
	@Override
	public int getInt(String key) throws IllegalArgumentException, NumberFormatException{
		if (properties.containsKey(key)) {
			String tmp = properties.getProperty(key);
			return Integer.valueOf(tmp);
		}
		throw new IllegalArgumentException("key["+key+"] is not exist.");
	}
	
	@Override
	public float getFloat(String key) throws IllegalArgumentException, NumberFormatException{
		if (properties.containsKey(key)) {
			String tmp = properties.getProperty(key);
			return Float.valueOf(tmp);
		}
		throw new IllegalArgumentException("key["+key+"] is not exist.");
	}
	
	@Override
	public double getDouble(String key) throws IllegalArgumentException, NumberFormatException{
		if (properties.containsKey(key)) {
			String tmp = properties.getProperty(key);
			return Double.valueOf(tmp);
		}
		throw new IllegalArgumentException("key["+key+"] is not exist.");
	}
	
	public void loadProperties(String fileName) {
		try {
			//FileInputStream fs = null;
			InputStream fs = null;
			try {
				String configFineName = fileName;
				//add this for loading config file base current env(dev, onlint, test).
				configFineName = EnvironmentUtil.getConfigName(configFineName);
				fs = ConfigUnit.class.getClassLoader().getResourceAsStream(configFineName);

				//fs = new FileInputStream(new File(url.getFile()));
				properties.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
