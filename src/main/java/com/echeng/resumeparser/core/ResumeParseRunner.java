package com.echeng.resumeparser.core;

import javax.annotation.Resource;

import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.common.utils.ResumeUtil;
import com.echeng.resumeparser.convert.FileConvertorStrategy;
import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.request.impl.ResumeParseRequest.ResumeParseRequest;
import com.echeng.resumeparser.merge.ResumesMerge;
import com.echeng.resumeparser.parser.ResumeParserStrategy;
import com.echeng.resumeparser.resumeInput.IResumeReaderStrategy;

public class ResumeParseRunner {
	
	@Resource(name="resumeReaderStrategy")
	private IResumeReaderStrategy resumeReader;
	
	@Resource(name="fileConvertorStrategy")
	private FileConvertorStrategy fileConvertor;
	
	@Resource(name="resumeParserStrategy")
	private ResumeParserStrategy resumeParser;
	
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
		String fileContent = fileConvertor.convert2Str(resume.getFileOri(), resume.getExt(), req.getConvertOptions());
		resume.setContent(fileContent);

		//parse
		ResumeParseResult parseRet = resumeParser.parse(resume, req.getParseOptions());

		//merge
		Resume finalResume = resumeMerger.merge(parseRet.getCandResumes(), req.getMergeOptions());
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

}
