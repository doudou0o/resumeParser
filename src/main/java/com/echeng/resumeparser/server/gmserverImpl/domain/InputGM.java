package com.echeng.resumeparser.server.gmserverImpl.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class InputGM {
	@Expose
	private RequestModel request;
	@Expose
	private Map<String, String> header;
	
	private String unpackType;
	
	private Header headerObj = new Header();

	public String getM(){
		return request.getM();
	}
	
	public String getC(){
		return request.getC();
	}
	
	public void setMList(List<String> mList){
		request.setMList(mList);
	}
	
	public LinkedHashMap<String, Object> getRequest(){
		return request.getP();
	}
	
	public Boolean isHeaderValid(){
		headerObj.setHeaderMap(header);
		return headerObj.isValid();
	}
	
	public Boolean isRequestValid(){
		return request.isValid();
	}
	
	public String getReqErrInfo(){
		return request.getErrInfo();
	}
	
	@Override
	public String toString(){
		return  header.toString() 
				+"\n"+ 
				request.toString();
	}
	
	@Data
	class RequestModel {
		@Expose
		private String c;
		@Expose
		private String m;
		@Expose
		private LinkedHashMap<String, Object> p;
		
		private List<String> mList;

		public Boolean isValid(){
			return (null != getErrInfo());
		}
		
		public String getErrInfo(){
			if (!"resume_parse".equals(c))
				return "the 'c' is invalid; ";
			if (!mList.contains(m))
				return "the 'm' is invalid; ";
			if (!RequestHelper.checkRequestP(m, p))
				return RequestHelper.getErrInfoByRequestP(m, p);
			return null;
		}
		
	}
	
}
