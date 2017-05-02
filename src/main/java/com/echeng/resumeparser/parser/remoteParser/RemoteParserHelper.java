package com.echeng.resumeparser.parser.remoteParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.echeng.resumeparser.common.utils.FileUtil;
import com.echeng.resumeparser.common.utils.gearmanTools.GearmanClientTool;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;

public class RemoteParserHelper {

	private final static String remoteParserName = "resume_parser_module";

	public static void send2RemoteParser(Resume ori_resume, ParseOption options, ResumeParseResult result) {
		//try
		@SuppressWarnings("unchecked")
		Map<String, Object> ret = GearmanClientTool.GearmanClientSubmit(
				buildRequest4RemoteParser(ori_resume, options),
				"icdc_basic", "msgpack", Map.class);

		System.out.println(ret);

	}
	
	private static Map<String, Object> buildRequest4RemoteParser(Resume resume, ParseOption options){
		String filename = resume.getOriName();
		String filetext = resume.getContent();
		byte[] fileori  = resume.getFileOri();
		
		Map<String, Object> o = new HashMap<String, Object>();
		o.put("runtype", options.getRunType());
		o.put("timeout", options.getTimeOut());
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("filename", filename);
		p.put("filetext", filetext);
		p.put("fileori", fileori);
		p.put("options", o);
		
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("c", "resume_parser_module");
		request.put("m", "resume_parse");
		request.put("p", p);
		
		return request;
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
