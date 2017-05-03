package com.echeng.resumeparser.parser.remoteParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.common.utils.FileUtil;
import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.gearmanTools.GearmanClientTool;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ParseOption;

public class RemoteParserHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(RemoteParserHelper.class);

	private final static String remoteParserName = "resume_parser_module";

	@SuppressWarnings("unchecked")
	public static void send2RemoteParser(Resume ori_resume, ParseOption options, ResumeParseResult result) {
		
		Map<String, Object> ret = null;
		
		try {
			ret = GearmanClientTool.GearmanClientSubmit(
					buildRequest4RemoteParser(ori_resume, options),
					remoteParserName, "json", Map.class);
			
		} catch (Exception e) {
			logger.error("call gearman remote parser worker failed...",e);
		}
		
		if (!(ret.containsKey("response") && ret.containsKey("results") && ret.get("results")==null))
			logger.error("call gearman remote parser worker return null...");
		
		
		String ans = JsonUtil.toJson(ret.get("results"));
		
		System.out.println(ans);

	}
	
	
	private static Map<String, Object> buildRequest4RemoteParser(Resume resume, ParseOption options){
		String filename = resume.getOriName();
		String filetext = resume.getContent();
		byte[] fileori  = resume.getFileOri();
		
		Map<String, Object> o = new HashMap<String, Object>();
		if (options.getRunType()!=null) o.put("runtype", options.getRunType());
		if (options.getTimeOut()>0) o.put("timeout", options.getTimeOut());
		if (options.getConstraint_degree()!=null) o.put("constraintdegree", options.getConstraint_degree());
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("filename", filename);
		p.put("filetext", filetext);
		p.put("fileori", fileori);
		p.put("options", o);
		
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("c", "resume_parser_module");
		request.put("m", "resume_parse");
		request.put("p", p);
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("request", request);
		root.put("header", o);
		
		return root;
	}
	
	public static void main(String[] args) {
		try {
			Resume r = new Resume();
			r.setOriName("1.txt");
			r.setContent(FileUtil.readFileToString(new File("D:/a.txt")));
			r.setFileOri(FileUtil.readFileToString(new File("D:/a.txt")).getBytes());
			
			ParseOption p = new ParseOption();
			p.setRunType(2);
			p.setTimeOut(20);
			RemoteParserHelper.send2RemoteParser(r, p, null);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
