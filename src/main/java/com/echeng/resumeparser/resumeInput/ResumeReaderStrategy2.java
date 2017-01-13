package com.echeng.resumeparser.resumeInput;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * 策略模式
 * 所有的策略临时获取
 */
public class ResumeReaderStrategy2 {
	
	private String filepath;
	private String groupname;
	private byte[] fileBytes;
	
	private IResumeReader m_ResumeReader;
	
//	@Autowired //由于Resource(j2ee提供)比Autowired(spring提供)更快
//	@Qualifier("fileReader")
	@Resource(name="fileReader")
	private IResumeReader resumeFileReader;
	@Resource(name="httpReader")
	private IResumeReader resumeHttpReader;
	@Resource(name="dfsReader")
	private IResumeReader resumeDfsReader;
	
	public ResumeReaderStrategy2(String groupname){
		this.m_ResumeReader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
		this.groupname = groupname;
	}
	
	public void readResume(String filepath){
		this.filepath = filepath;
		fileBytes = m_ResumeReader.readResume(this.filepath, groupname);
	}

	public byte[] getOriFile(){
		return fileBytes;
	}
	
	public void setGroupname(String groupname){
		this.m_ResumeReader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
		this.groupname = groupname;
	}

	private IResumeReader getInstanceResumeReader(ResumeInputType resumeInputType) {
		switch (resumeInputType) {
			case LOCAL:
				return resumeFileReader;
			case HTTP:
				return resumeHttpReader;
			case DFS:
				return resumeDfsReader;
			default:
				return null;
		}
	}

}
