package com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest;

import lombok.Data;

@Data
public class ParseOption {
	private Integer runType;
	private long timeOut;
	private Integer constraint_degree;
	
}
