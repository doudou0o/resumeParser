package com.echeng.resumeparser.parser;

import com.echeng.resumeparser.domain.resume.Resume;

public interface IResumeParser {
	public void feed(Resume resume);
	public void parse();
}