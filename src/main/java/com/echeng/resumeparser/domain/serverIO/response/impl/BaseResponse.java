package com.echeng.resumeparser.domain.serverIO.response.impl;

import com.echeng.resumeparser.domain.serverIO.response.Response;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class BaseResponse implements Response {
	@Expose
	private Integer err_no;
	@Expose
	private String err_msg;

	@Override
	public void setErrorNo(Integer errno) {
		setErr_no(errno);
	}
	@Override
	public void setErrorMsg(String errmsg) {
		setErr_msg(errmsg);
	}
}
