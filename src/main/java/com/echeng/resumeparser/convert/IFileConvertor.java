package com.echeng.resumeparser.convert;

import com.echeng.resumeparser.domain.serverIO.ConvertOption;

public interface IFileConvertor {
	public void init();
	public void feed(byte[] fileBytes);
	public void convert(byte[] fileBytes);
	public void convert();
	public void setOptions(ConvertOption options);
	public String getFileContent();
	//public String getFeatures();
}
