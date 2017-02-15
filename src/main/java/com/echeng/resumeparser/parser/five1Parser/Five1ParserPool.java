package com.echeng.resumeparser.parser.five1Parser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.parser.IResumeParserPool;

public class Five1ParserPool implements IResumeParserPool {

	ExecutorService m_ParserPool;
	
	public Five1ParserPool(){
		m_ParserPool = Executors.newCachedThreadPool();
	}
	
	public Five1ParserPool(ExecutorService executorService){
		this.m_ParserPool = executorService;
	}
	

	@Override
	public Future<?> parse(Resume resume, ParseOption options, ResumeParseResult result) {
		Five1Parser parser = new Five1Parser();
		parser.feed(resume);
		parser.setParseResult(result);
		parser.setOption(options);
		return m_ParserPool.submit(parser);
	}

}
