package com.echeng.resumeparser.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.ResumeUtil;
import com.echeng.resumeparser.convert.FileConvertorStrategy;
import com.echeng.resumeparser.convert.IFileConvertor;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.merge.ResumesMerge;
import com.echeng.resumeparser.parser.ParserPool;
import com.echeng.resumeparser.resumeInput.IResumeReaderStrategy;

public class ResumeParseRuner {
	//private ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mainComponent.xml");
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.SPRING_FILE);
	
	public ResumeParseResult run(Resume resume){
		resume.setExt(ResumeUtil.getExtFromResume(resume));

		//read
		//ResumeReaderStrategy resumeReader = new ResumeReaderStrategy(resume.getGroupName());
		IResumeReaderStrategy resumeReader = (IResumeReaderStrategy) ctx.getBean("resumeReaderStrategy");
		resumeReader.readResume(resume.getGroupName(), resume.getFileName());
		resume.setFileOri(resumeReader.getOriFile());

		//convert
		IFileConvertor convertor = new FileConvertorStrategy(resume.getExt());
		convertor.feed(resume.getFileOri());
		convertor.convert();
		resume.setContent(convertor.getFileContent());

		//parse
		ResumeParseResult parseRet = new ParserPool().parse(resume);

		//merge
		Resume finalResume = ResumesMerge.merge(parseRet.getCandResumes());
		parseRet.setFinalResume(finalResume);

		//json
		String standardJson = JsonUtil.resumeToJson(finalResume, true);
		String completeJson = JsonUtil.resumeToJson(finalResume, false);
		parseRet.setStandardJson(standardJson);
		parseRet.setStandardJson(completeJson);
		
		return parseRet;
	}


	public static void main(String[] args) {
		//BasicConfigurator.configure();
		new ResumeParseRuner().run(new Resume("testResume/test.txt","local"));
	}
}
