package com.echeng.resumeparser.parser.remoteParser;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.parser.IResumeParser;

public class RemoteParser implements IResumeParser {
	
	private final String M_NAME = "remoteparser";
	
	private ParseOption options;
	private ResumeParseResult result;
	private Resume ori_resume;

	
	@Override
	public void run() {
		parse();
	}

	@Override
	public String getParserName() {
		return M_NAME;
	}

	@Override
	public void setOption(ParseOption options) {
		this.options = options;
	}

	@Override
	public void setParseResult(ResumeParseResult result) {
		this.result = result;
	}

	@Override
	public void feed(Resume resume) {
		this.ori_resume = resume;
	}

	@Override
	public void parse() {
		RemoteParserHelper.send2RemoteParser(ori_resume, options, result);
	}

}
