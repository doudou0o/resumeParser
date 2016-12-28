package com.echeng.resumeparser.common.utils;

import com.echeng.resumeparser.domain.resume.Resume;

public class resumeUtil {
	
	public static String getExtFromResume(Resume resume){
		String[] ns = resume.getFileName().split(".");
		return ns[ns.length-1];
	}
	
}
