package com.echeng.resumeparser.server.gmserverImpl.domain;

import com.echeng.resumeparser.domain.serverIO.response.Response;

import lombok.Data;

@Data
public class OutputGM {
	private Header header;
	private Response response;
	
	public OutputGM(){}

	public OutputGM(Header header, Response response) {
		this.header = header;
		this.response = response;
	}
	
}
