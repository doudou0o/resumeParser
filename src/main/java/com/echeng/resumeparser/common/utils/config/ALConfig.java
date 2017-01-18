package com.echeng.resumeparser.common.utils.config;

public interface ALConfig {
	public String getString(String key) throws IllegalArgumentException;
	public int getInt(String key)  throws IllegalArgumentException, NumberFormatException;
	public float getFloat(String key)  throws IllegalArgumentException, NumberFormatException;
	public double getDouble(String key)  throws IllegalArgumentException, NumberFormatException;
}
