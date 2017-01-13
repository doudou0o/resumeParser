       package com.echeng.resumeparser.convert.convertors;

import com.echeng.resumeparser.convert.IFileConvertor;

public class TxtConvertor implements IFileConvertor {
	private byte[] fileBytes;
	private String filecontent;

	public void feed(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public void convert(byte[] fileBytes) {
		feed(fileBytes);
		convert();
	}

	public void convert() {
		this.filecontent = new String(fileBytes);
	}

	public void setSaveFeasture(Boolean isSave) {
		// TODO Auto-generated method stub
		
	}

	public String getFileContent() {
		return this.filecontent;
	}

	public String getLineFeature() {
		// TODO Auto-generated method stub
		return null;
	}

}
