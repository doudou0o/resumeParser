package com.echeng.resumeparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.parser.five1Parser.Five1ParserPool;

public class ResumeParserStrategy {
	
	@Resource
	private Five1ParserPool five1ParserPool;

	public ResumeParseResult parse(Resume resume, ParseOption options){
		ResumeParseResult ret = new ResumeParseResult();
		
		List<Future<?>> tasks = new ArrayList<Future<?>>(10);
	
		tasks.add(five1ParserPool.parse(resume, options, ret));

		try {
			Thread.sleep(options.getTimeOut());
			for (Future<?> fut : tasks) {
				if (!fut.isDone()) {
					System.out.println("Task " + fut + " has not finshed!");
					fut.cancel(true);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
