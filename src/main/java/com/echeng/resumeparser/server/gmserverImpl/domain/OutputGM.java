package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.response.Response;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class OutputGM {
	@Expose
	private Map<String, String> header;
	@Expose
	private Response response;
	
	private Header headerObj;

	public OutputGM(){}

	public OutputGM(Header headerObj, Response response) {
		this.headerObj = headerObj;
		this.response = response;
		this.header = headerObj.getHeaderMap();
	}
	
}
