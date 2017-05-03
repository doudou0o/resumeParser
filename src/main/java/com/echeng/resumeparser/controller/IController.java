package com.echeng.resumeparser.controller;

import java.util.Map;

import com.echeng.resumeparser.domain.serverIO.IResponse;

public interface IController {
	public Boolean checkRequest(Map<String,Object> req);
	public String getErrmsgByReq(Map<String,Object> req);
	public IResponse handle(Map<String,Object> req);
}
