package com.echeng.resumeparser.domain.resume;

import java.util.List;

import com.google.gson.annotations.Expose;

import lombok.Data;


@Data
public class Resume {
	private String fileName;
	private String groupName;
	private String oriName;
	private String ext;
	private byte[] fileOri;

	@Expose
	private String content;

	@Expose
	private BasicInfo basic;
	@Expose
	private List<Education> education;
	@Expose
	private List<Employment> work;
	@Expose
	private List<Project> project;
	
	public Resume(String fileName, String groupName){
		this.fileName  = fileName;
		this.groupName = groupName;
	}
	public Resume(){}
}
