package com.echeng.resumeparser.core;

import java.util.LinkedHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.IRequest;
import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ResumeParseRequest;


public class LocalMain {
	
	private final String CONTEXT = "properties/spring/mainContext.xml";
	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);
	
	public static void main(String[] args) {
		new LocalMain().run(args);
	}
	
	@SuppressWarnings("unchecked")
	public void run(String[] args){
		String reqStrJson = "{\"filename\":\"1\",\"groupname\":\"local\",\"option\":{\"constraint_degree\":1,\"run_type\":1}}";
		IRequest mock = RequestHelper.buildResumeParseReq(
				JsonUtil.fromJson(reqStrJson, LinkedHashMap.class));

		ResumeParseRunner runner = (ResumeParseRunner) ctx.getBean("resumeParseRunner");

		runner.run(new Resume("testResume/test.txt", "local"), (ResumeParseRequest) mock);
	}
}


