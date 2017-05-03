package com.echeng.resumeparser.parser;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;

public interface IResumeParser extends Runnable {
	public String getParserName();
	public void setOption(ParseOption options);
	public void setParseResult(ResumeParseResult result);
	public void feed(Resume resume);
	public void parse();
}