package com.echeng.resumeparser.common.utils;

import com.echeng.resumeparser.domain.resume.Resume;

public class ResumeUtil {
	
	public static String getExtFromResume(Resume resume){
		String[] ns = resume.getFileName().split("\\.");
		if (ns.length < 2)
			return "";
		return ns[ns.length-1];
	}
	
}
