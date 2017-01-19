package com.echeng.resumeparser.convert.convertors;

import java.io.UnsupportedEncodingException;

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
		//TODO 判断编码
		try {
			this.filecontent = new String(fileBytes, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
