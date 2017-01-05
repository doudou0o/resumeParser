package com.echeng.resumeparser.domain;

import java.util.List;

import com.echeng.resumeparser.domain.resume.Resume;

import lombok.Data;

@Data
public class ResumeParseResult {
	private List<Resume> candResumes;
	private Resume finalResume;

	private String standardJson;
	private String completeJson;
	
	//TODO
	private String parseInfo;
	
}
