package com.echeng.resumeparser.convert.convertors;


import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.convert.IFileConvertor;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ConvertOption;

/**
 * this converter dependent on "pdfbox"
 * used to convert pdf and this is good for now(other converter will give repeated words)
 * 
 * 
 */
public class PdfConvertor implements IFileConvertor {
	
	private static final Logger logger = LoggerFactory.getLogger(PdfConvertor.class);
	
	private String fileContent;
	private byte[] m_fileBytes;
	private ConvertOption options;

	@Override
	public void init() {
		// TODO
		// in this class option is not used
		if (options != null)
			return;
	}

	@Override
	public void feed(byte[] fileBytes) {
		this.m_fileBytes = fileBytes;
	}

	@Override
	public void convert(byte[] fileBytes) {
		try{
			PDDocument pdf = PDDocument.load(fileBytes);
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setSortByPosition(true);
			String plainText = stripper.getText(pdf);
			pdf.close();
			setFileContent(plainText);
		}catch (IOException ex){
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void convert() {
		convert(this.m_fileBytes);
	}

	@Override
	public void setOptions(ConvertOption options) {
		this.options = options;
	}
	
	private void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	@Override
	public String getFileContent() {
		return fileContent;
	}

}
