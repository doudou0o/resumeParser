package com.echeng.resumeparser.core;

import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.resumeUtil;
import com.echeng.resumeparser.convert.FileConvertorStrategy;
import com.echeng.resumeparser.convert.IFileConvertor;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.merge.ResumesMerge;
import com.echeng.resumeparser.parser.ParserPool;
import com.echeng.resumeparser.resumeInput.ResumeReaderStrategy;

public class ResumeParseRuner {
	//private ApplicationContext ctx;
	
	public void run(Resume resume){
		resume.setExt(resumeUtil.getExtFromResume(resume));
	
		//read
		ResumeReaderStrategy resumeReader = new ResumeReaderStrategy(resume.getGroupName());
		resumeReader.readResume(resume.getFileName());
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

	}


	public static void main(String[] args) {
		new ResumeParseRuner().run(new Resume("local1","filename.txt"));
	}
}
