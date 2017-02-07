package com.echeng.resumeparser.core;

import java.util.LinkedHashMap;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.response.IResponse;
import com.echeng.resumeparser.domain.serverIO.response.impl.ResumeParseResponse;

public class ParseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseController.class);
	
	private ResumeParseRuner resumeParseRunner;
	
	public IResponse handle(String m, LinkedHashMap<String, Object> reqDict) {
		if (Constant.M_NAMEFILTER.equals(m)){
			ResumeParseResult result = resumeParseRunner.run( buildOriResumeInfo(reqDict) );
			ResumeParseResponse resp = new ResumeParseResponse();
			//resp.setResults(results);
			return resp;
			
		}
		if (Constant.M_RESUMEPARSE.equals(m)){
			
		}
		return null;
	}

	private Resume buildOriResumeInfo(LinkedHashMap<String, Object> reqDict) {
		// TODO Auto-generated method stub
		return null;
	}
}
