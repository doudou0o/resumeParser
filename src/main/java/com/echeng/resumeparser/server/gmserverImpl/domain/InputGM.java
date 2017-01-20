package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.List;

import com.echeng.resumeparser.domain.serverIO.request.Request;

import lombok.Data;

@Data
public class InputGM {
	private Header header;
	private RequestModel requestModel;
	
	public String getM(){
		return requestModel.getM();
	}
	
	public String getC(){
		return requestModel.getC();
	}
	
	public void setMList(List<String> mList){
		requestModel.setMList(mList);
	}
	
	public Request getRequest(){
		return requestModel.getRequest();
	}
	
	public Boolean isHeaderValid(){
		return header.isValid();
	}
	
	public Boolean isRequestValid(){
		return requestModel.isValid();
	}
	
	public String getReqErrInfo(){
		return requestModel.getErrInfo();
	}
	
	
	@Data
	class RequestModel {
		private String c;
		private String m;
		private Request request;
		
		private List<String> mList;
		
		public Boolean isValid(){
			return (null != getErrInfo());
		}
		
		public String getErrInfo(){
			if (!"resume_parse".equals(c))
				return "the 'c' is invalid; ";
			if (!mList.contains(m))
				return "the 'm' is invalid; ";
			if (!request.isValid())
				return request.getReqErrInfo();
			return null;
		}
	}
}
