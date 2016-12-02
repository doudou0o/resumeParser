package com.echeng.resumeparser.resumeInput;

import com.echeng.resumeparser.resumeInput.readers.ResumeDfsReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeFileReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeHttpReader;

public class ResumeReaderContext {

	private IResumeReader m_ResumeReader;
	private String filepath;
	private String groupname;
	
	public ResumeReaderContext(String groupname){
		this.m_ResumeReader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
	}
	
	public void readResume(String filepath){
		this.filepath = filepath;
		m_ResumeReader.readResume(this.filepath, groupname);
	}

	public void getOriFile(){
		m_ResumeReader.getResumeOriFile();
	}


	private IResumeReader getInstanceResumeReader(ResumeInputType resumeInputType) {
		switch (resumeInputType) {
			case LOCAL:
				return new ResumeFileReader();
			case HTTP:
				return new ResumeHttpReader();
			case DFS:
				return new ResumeDfsReader();
			default:
				return null;
		}
	}



}
