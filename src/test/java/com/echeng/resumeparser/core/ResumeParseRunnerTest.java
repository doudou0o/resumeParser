package com.echeng.resumeparser.core;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.echeng.resumeparser.BaseTestCase;
import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;
import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest;

public class ResumeParseRunnerTest extends BaseTestCase {
	
	private static ResumeParseRunner runner;

	@BeforeClass
	public static void setUpBeforeClass() {
		BaseTestCase.setUpBeforeClass();
		runner = (ResumeParseRunner) ctx_static.getBean("resumeParseRunner");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1(){
		String reqStr = "{\"filename\":\"1\",\"groupname\":\"local\",\"option\":{\"constraint_degree\":1,\"run_type\":1}}";
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Object> reqDict = JsonUtil.fromJson(reqStr, LinkedHashMap.class);
		IRequest mock = RequestHelper.buildResumeParseReq(reqDict);
		runner.run(new Resume("testResume/test.txt","local"), (ResumeParseRequest) mock);
	}
}