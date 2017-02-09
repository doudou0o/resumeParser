package com.echeng.resumeparser.convert.convertors;

import java.io.UnsupportedEncodingException;

import com.echeng.resumeparser.convert.IFileConvertor;
import com.echeng.resumeparser.domain.serverIO.ConvertOption;

public class TxtConvertor implements IFileConvertor {
	private byte[] fileBytes;
	private String filecontent;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void feed(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	@Override
	public void convert(byte[] fileBytes) {
		feed(fileBytes);
		convert();
	}

	@Override
	public void convert() {
		//TODO 判断编码
		try {
			this.filecontent = new String(fileBytes, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setOptions(ConvertOption options) {
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
