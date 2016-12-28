package com.echeng.resumeparser.domain.resume;

import java.util.List;

import lombok.Data;


@Data
public class Resume {
	private String fileName;
	private String groupName;
	private String oriName;
	private String ext;
	private String content;
	private byte[] fileOri;
	
	private BasicInfo basic;
	private List<Education> education;
	private List<Employment> work;
	private List<Project> project;
	
	private String standardJson;
	private String completeJson;
	
	public Resume(String fileName, String groupName){
		this.fileName  = fileName;
		this.groupName = groupName;
	}
	public Resume(){}
}
