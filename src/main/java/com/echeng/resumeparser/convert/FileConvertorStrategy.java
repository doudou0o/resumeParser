package com.echeng.resumeparser.convert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.convert.convertors.DocConvertor;
import com.echeng.resumeparser.convert.convertors.HtmlConvertor;
import com.echeng.resumeparser.convert.convertors.PdfConvertor;
import com.echeng.resumeparser.convert.convertors.TxtConvertor;

/**
 * 代理模式
 * 所有被代理器临时获取
 *
 */
public class FileConvertorStrategy{


	public String convert2Str(byte[] fileBytes, String ext, Map<String, Object> options){
		IFileConvertor convertor = getConvertorInstance(ext);
		convertor.feed(fileBytes);
		convertor.setOptions(options);
		convertor.convert();
		//convertor.getFeatures();
		String fileContent = convertor.getFileContent();
		return fileContent;
	}
	
	public List<String> convert2Lines(byte[] fileBytes, String ext, Map<String, Object> options){
		return Arrays.asList(convert2Str(fileBytes, ext, options).split("\n"));
	}

	/*
	 * Temporarily:
	 * Every time invoked, the function will return a new instance.
	 * It will make a great number of memory fragmentation when frequently invoke.
	 * #TODO will make a instance pool to prevent.
	 */
	private IFileConvertor getConvertorInstance(String ext) {
		if (Constant.TXT.equals(ext)){
			return new TxtConvertor();
		}
		if (Constant.DOC.equals(ext)){
			return new DocConvertor();
		}
		if (Constant.PDF.equals(ext)){
			return new PdfConvertor();
		}
		if (Constant.HTML.equals(ext)){
			return new HtmlConvertor();
		}
		return new TxtConvertor();
	}

}
