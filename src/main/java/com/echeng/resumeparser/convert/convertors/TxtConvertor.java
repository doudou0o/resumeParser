package com.echeng.resumeparser.convert.convertors;

import java.io.UnsupportedEncodingException;

import com.echeng.resumeparser.convert.IFileConvertor;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ConvertOption;

/**
 * keep the text encoded by UTF-8 !!
 * before it can judge the encode of text
 *
 */
public class TxtConvertor implements IFileConvertor {
	private byte[] fileBytes;
	private String filecontent;

	@Override
	public void init() {
		
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
		//暂行文本传入时保证utf8编码
		try {
			this.filecontent = new String(fileBytes, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setOptions(ConvertOption options) {
	}

	public String getFileContent() {
		return this.filecontent;
	}

	public String getLineFeature() {
		return null;
	}

}
