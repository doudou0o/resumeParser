package com.echeng.resumeparser.core;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.ResumeUtil;
import com.echeng.resumeparser.convert.FileConvertorStrategy;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest;
import com.echeng.resumeparser.merge.ResumesMerge;
import com.echeng.resumeparser.parser.ParserPool;
import com.echeng.resumeparser.resumeInput.IResumeReaderStrategy;

public class ResumeParseRunner {
	
	@Resource(name="resumeReaderStrategy")
	private IResumeReaderStrategy resumeReader;
	
	@Resource(name="fileConvertorStrategy")
	private FileConvertorStrategy fileConvertor;
	
	@Resource(name="resumesMerge")
	private ResumesMerge resumeMerger;

	
	public ResumeParseResult run(ResumeParseRequest req){
		return run(buildOriResume(req), req);
	}
	
	public ResumeParseResult run(Resume resume, ResumeParseRequest req){
		
		resume.setExt(ResumeUtil.getExtFromResume(resume));

		//read
		byte[] oriFileBytes = resumeReader.readResume(resume.getGroupName(), resume.getFileName());
		resume.setFileOri(oriFileBytes);

		//convert
		String fileContent = fileConvertor.convert2Str(resume.getFileOri(), resume.getExt(), null);//TODO --null
		resume.setContent(fileContent);

		//parse
		ResumeParseResult parseRet = new ParserPool().parse(resume);

		//merge
		Resume finalResume = resumeMerger.merge(parseRet.getCandResumes());
		parseRet.setFinalResume(finalResume);

		//json
		String standardJson = JsonUtil.resumeToJson(finalResume, true);
		String completeJson = JsonUtil.resumeToJson(finalResume, false);
		parseRet.setStandardJson(standardJson);
		parseRet.setStandardJson(completeJson);
		
		return parseRet;
	}

	private Resume buildOriResume(ResumeParseRequest req){
		return new Resume(req.getFileName(), req.getGroupName());
	}
	
	

	//test
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.SPRING_FILE);
		ResumeParseRequest mock = new ResumeParseRequest();
		new ResumeParseRunner().run(new Resume("testResume/test.txt","local"), mock);
	}
}
