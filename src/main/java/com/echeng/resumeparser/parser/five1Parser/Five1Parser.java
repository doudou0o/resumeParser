package com.echeng.resumeparser.parser.five1Parser;

import java.util.List;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;
import com.echeng.resumeparser.model.DivideModel;
import com.echeng.resumeparser.parser.IResumeParser;

public class Five1Parser implements IResumeParser {

	private final String M_NAME = "five1parser";

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
		List<String> lines = DivideModel.divide(ori_resume.getContent());
		// TODO Auto-generated method stub
	}

}
