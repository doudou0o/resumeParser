package com.echeng.resumeparser.parser.zhaopinParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.parser.IResumeParserPool;

public class ZhaopinParserPool implements IResumeParserPool {

	ExecutorService m_ParserPool;

	public ZhaopinParserPool(){
		m_ParserPool = Executors.newCachedThreadPool();
	}

	public ZhaopinParserPool(ExecutorService executorService){
		this.m_ParserPool = executorService;
	}


	@Override
	public Future<?> parse(Resume resume, ParseOption options, ResumeParseResult result) {
		ZhaopinParser parser = new ZhaopinParser();
		parser.feed(resume);
		parser.setParseResult(result);
		parser.setOption(options);
		return m_ParserPool.submit(parser);
	}
}
