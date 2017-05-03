package com.echeng.resumeparser.parser;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;
import com.echeng.resumeparser.parser.remoteParser.RemoteParser;

public class ResumeParserStrategyRC {

	public ResumeParseResult parse(Resume resume, ParseOption options){
		ResumeParseResult ret = new ResumeParseResult();

		RemoteParser rp = new RemoteParser();

		rp.feed(resume);
		rp.setOption(options);
		rp.setParseResult(ret);
		rp.run();

		return ret;
	}
}
