package com.echeng.resumeparser.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.echeng.resumeparser.BaseTestCase;
import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;

public class RequestHelperTest extends BaseTestCase {

	@Test
	public void test1(){
		Map<String, Object> reqDict = new LinkedHashMap<String, Object>();
		reqDict.put("filename", "11");
		reqDict.put("groupname", "11");
		assertTrue(RequestHelper.checkRequestP("resume_parse", reqDict));
		
		reqDict.remove("filename");
		assertFalse(RequestHelper.checkRequestP("resume_parse", reqDict));
		reqDict.remove("groupname");
		assertFalse(RequestHelper.checkRequestP("resume_parse", reqDict));
		reqDict.put("groupname", "11");
		assertFalse(RequestHelper.checkRequestP("resume_parse", reqDict));
	}
}
