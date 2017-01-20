package com.echeng.resumeparser.domain.serverIO.request;

public interface Request {
	public Boolean isValid();
	public String getReqErrInfo();
}
