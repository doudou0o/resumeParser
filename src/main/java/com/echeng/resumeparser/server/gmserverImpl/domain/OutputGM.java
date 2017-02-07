package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.response.IResponse;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class OutputGM {
	
	@Expose
	private Map<String, String> header;
	@Expose
	private ResponseModel response = new ResponseModel();
	
	private Header headerObj;

	public OutputGM(){}

	public OutputGM(Header headerObj, IResponse response) {
		this.headerObj = headerObj;
		this.header = headerObj.getHeaderMap();
		setResponse(response);
	}
	
	public void setHeaderObj(Header headerObj){
		this.headerObj = headerObj;
		this.header = headerObj.getHeaderMap();
	}
	
	public void setErrorNo(Integer err_no){
		this.response.setErr_no(err_no);
	}
	
	public void setErrorMsg(String err_msg){
		this.response.setErr_msg(err_msg);
	}
	
	public void setResponse(IResponse results){
		this.response.setResults(results);
	}
	
	
	@Data
	class ResponseModel {
		@Expose
		private Integer err_no;
		@Expose
		private String err_msg;
		@Expose
		private IResponse results;
	}
}
