package com.echeng.resumeparser.parser;

import com.echeng.resumeparser.domain.ResumeParseResult;
import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.ParseOption;

public class ResumeParserStrategy {

	public ResumeParseResult parse(Resume resume, ParseOption options){
		
		return null;
	}


	/*
	 * Temporarily:
	 * Every time invoked, the function will return a new instance.
	 * It will make a great number of memory fragmentation when frequently invoke.
	 * #TODO will make a instance pool to prevent.
	 */
}
