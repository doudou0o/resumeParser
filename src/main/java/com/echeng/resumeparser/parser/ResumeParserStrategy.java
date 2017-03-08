package com.echeng.resumeparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.parser.five1Parser.Five1ParserPool;
import com.echeng.resumeparser.parser.zhaopinParser.ZhaopinParserPool;

public class ResumeParserStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeParserStrategy.class);
	
	@Resource
	private Five1ParserPool five1ParserPool;
	@Resource
	private ZhaopinParserPool zhaopinParserPool;

	public ResumeParseResult parse(Resume resume, ParseOption options){
		ResumeParseResult ret = new ResumeParseResult();

		List<Future<?>> tasks = new ArrayList<Future<?>>(10);
	
		tasks.add(five1ParserPool.parse(resume, options, ret));
		tasks.add(zhaopinParserPool.parse(resume, options, ret));

		waitandkill(tasks, options.getTimeOut());

		return ret;
	}
	
	private void waitandkill(List<Future<?>> tasks, long timeout){
		try {
			Thread.sleep(timeout);
			for (Future<?> fut : tasks) {
				if (!fut.isDone()) {
					logger.error("Task " + fut + " has not finshed!");
					fut.cancel(true);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
