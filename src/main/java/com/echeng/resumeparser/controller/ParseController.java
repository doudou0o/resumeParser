package com.echeng.resumeparser.controller;

import java.util.Map;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.common.utils.SpringContextsUtil;
import com.echeng.resumeparser.core.ResumeParseRunner;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.serverIO.IRequest;
import com.echeng.resumeparser.domain.serverIO.IResponse;
import com.echeng.resumeparser.domain.serverIO.request.RequestHelper;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ResumeParseRequest;
import com.echeng.resumeparser.domain.serverIO.response.impl.ResumeParseResponse;

/**
 * the controller 
 * 
 *
 */
public class ParseController implements IController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParseController.class);

	
	private IResponse resumeParse(Map<String, Object> reqDict){

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


	@Override
	public Boolean checkRequest(Map<String, Object> req) {
		return null == getErrmsgByReq(req);
	}


	@Override
	public String getErrmsgByReq(Map<String, Object> reqDicP) {
		if (null == reqDicP)
			return "no request parameter in ";
		if (!reqDicP.containsKey("groupname") || "".equals(reqDicP.get("groupname")))
			return "no 'groupname' in request parameters";
		if (!reqDicP.containsKey("filename") || "".equals(reqDicP.get("filename")))
			return "no 'filename' in request parameters";
		return null;
	}


	@Override
	public IResponse handle(Map<String, Object> req) {
		return resumeParse(req);
	}

}
