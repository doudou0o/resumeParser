package com.echeng.resumeparser.domain.serverIO.response.impl;

import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.response.Response;

import lombok.Data;

@Data
public class ResumeParseResponse implements Response {
	private Integer err_no;
	private String err_msg;
	private Resume results;

	@Override
	public void setErrorNo(Integer errno) {
		setErr_no(errno);
	}
	@Override
	public void setErrorMsg(String errmsg) {
		setErr_msg(errmsg);
	}

}
