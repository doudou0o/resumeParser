package com.echeng.resumeparser.domain.serverIO.response.impl;

import com.echeng.resumeparser.domain.resume.Resume;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper=false)
@Data
public class ResumeParseResponse extends BaseResponse {
	private Resume results;
}
