package com.echeng.resumeparser.convert;

public interface IFileConvertor {
	public void feed(byte[] fileBytes);
	public void convert(byte[] fileBytes);
	public void convert();
	public void setSaveFeasture(Boolean isSave);
	public String getFileContent();
	public String getLineFeature();
}
