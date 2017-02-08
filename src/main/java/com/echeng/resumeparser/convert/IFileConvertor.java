package com.echeng.resumeparser.convert;

import java.util.Map;

public interface IFileConvertor {
	public void init();
	public void feed(byte[] fileBytes);
	public void convert(byte[] fileBytes);
	public void convert();
	public void setOptions(Map<String, Object> options);
	public String getFileContent();
	//public String getFeatures();
}
