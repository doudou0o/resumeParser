package com.echeng.resumeparser.convert;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.convert.convertors.DocConvertor;
import com.echeng.resumeparser.convert.convertors.PdfConvertor;

/**
 * 代理模式
 * 所有被代理器临时获取
 *
 */
public class FileConvertorStrategy implements IFileConvertor {

	private IFileConvertor m_FileConvector;

	public FileConvertorStrategy(String ext){
		if (Constant.TXT.equals(ext)){
			m_FileConvector = new DocConvertor();
		}
		if (Constant.DOC.equals(ext)){
			m_FileConvector = new DocConvertor();
		}
		if (Constant.PDF.equals(ext)){
			m_FileConvector = new PdfConvertor();
		}
	}

	public void feed(byte[] fileBytes) {
		m_FileConvector.feed(fileBytes);
	}

	public void convert() {
		m_FileConvector.convert();		
	}

	public void setSaveFeasture(Boolean isSave) {
		m_FileConvector.setSaveFeasture(isSave);
	}

	public String getFileContent() {
		return m_FileConvector.getFileContent();
	}

	public String getLineFeature() {
		return m_FileConvector.getLineFeature();
	}

	
}
