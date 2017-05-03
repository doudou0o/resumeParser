package com.echeng.resumeparser.parser;

import java.util.concurrent.Future;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;

public interface IResumeParserPool {
	public Future<?> parse(Resume resume, ParseOption options, ResumeParseResult result);
}
