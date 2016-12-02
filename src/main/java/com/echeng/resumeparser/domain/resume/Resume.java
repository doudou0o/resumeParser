package com.echeng.resumeparser.domain.resume;

import java.util.List;

import lombok.Data;


@Data
public class Resume {
	private BasicInfo basic;
	private List<Education> education;
	private List<Employment> work;
	private List<Project> project;
}
