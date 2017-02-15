package com.echeng.resumeparser.domain;

import java.util.List;
import java.util.Vector;

import com.echeng.resumeparser.domain.resume.Resume;

import lombok.Data;

@Data
public class ResumeParseResult {
	private List<Resume> candResumes = new Vector<Resume>();
	private Resume finalResume;

	private String standardJson;
	private String completeJson;
	
	//TODO
	private StringBuilder parseInfo;
	
}
