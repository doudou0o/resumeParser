package com.echeng.resumeparser.parser.geneParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;
import com.echeng.resumeparser.parser.IResumeParserPool;

public class GeneralParserPool implements IResumeParserPool {

	ExecutorService m_ParserPool;

	public GeneralParserPool(){
		m_ParserPool = Executors.newCachedThreadPool();
	}

	public GeneralParserPool(ExecutorService executorService){
		this.m_ParserPool = executorService;
	}


	@Override
	public Future<?> parse(Resume resume, ParseOption options, ResumeParseResult result) {
		GeneralParser parser = new GeneralParser();
		parser.feed(resume);
		parser.setParseResult(result);
		parser.setOption(options);
		return m_ParserPool.submit(parser);
	}
}
