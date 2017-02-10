package com.echeng.resumeparser.core;

import java.util.LinkedHashMap;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.common.utils.SpringContextsUtil;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;
import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest;
import com.echeng.resumeparser.domain.serverIO.response.IResponse;
import com.echeng.resumeparser.domain.serverIO.response.impl.ResumeParseResponse;

public class ParseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseController.class);


	public IResponse handle(String m, LinkedHashMap<String, Object> reqDict) {
		if (Constant.M_NAMEFILTER.equals(m))
			return resumeParse(reqDict);

		if (Constant.M_RESUMEPARSE.equals(m)){
			
		}

		return null;
	}


	private IResponse resumeParse(LinkedHashMap<String, Object> reqDict){

		IRequest req = RequestHelper.buildResumeParseReq(reqDict);
		
		// new one runner
		ResumeParseRunner resumeParseRunner = (ResumeParseRunner) SpringContextsUtil.getBean("resumeParseRunner");
		ResumeParseResult result = resumeParseRunner.run((ResumeParseRequest) req);
		
		// print log result
		logger.debug("standard result:\n"+result.getStandardJson());
		logger.debug("complete result:\n"+result.getCompleteJson());
		logger.debug("parse info:\n"+result.getParseInfo());

		ResumeParseResponse resp = new ResumeParseResponse();
		resp.setResults(result.getFinalResume());
		return resp;
	}

}
