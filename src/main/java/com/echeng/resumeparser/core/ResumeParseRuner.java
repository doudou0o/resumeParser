package com.echeng.resumeparser.core;

import java.util.List;

import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.resumeUtil;
import com.echeng.resumeparser.convert.FileConvertorStrategy;
import com.echeng.resumeparser.convert.IFileConvertor;
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
		List<Resume> parsed_results = new ParserPool().parse(resume);
	
		//merge
		Resume finalresult = ResumesMerge.merge(parsed_results);

		//json
		String standardJson = JsonUtil.resumeToJson(finalresult, true);
		String completeJson = JsonUtil.resumeToJson(finalresult, false);
		finalresult.setStandardJson(standardJson);
		finalresult.setStandardJson(completeJson);
	}


	public static void main(String[] args) {
		new ResumeParseRuner().run(new Resume("local1","filename.txt"));
	}
}