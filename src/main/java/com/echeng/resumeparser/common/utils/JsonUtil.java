package com.echeng.resumeparser.common.utils;

import com.echeng.resumeparser.domain.resume.Resume;

public class JsonUtil {
	public static String resumeToJson(Resume resume){
		return resumeToJson(resume, true);
	}
	
	public static String resumeToJson(Resume resume, Boolean isStandard){
		if (isStandard)
			return resumeToStandardJson(resume);
		else
			return resumeToCompleteJson(resume);
	}
	
	private static String resumeToStandardJson(Resume resume){
		return "";
	}
	
	private static String resumeToCompleteJson(Resume resume){
		return "";
	}
}
