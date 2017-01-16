package com.echeng.resumeparser.resumeInput;

import com.echeng.resumeparser.resumeInput.readers.ResumeDfsReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeFileReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeHttpReader;

/**
 * 策略模式
 * 所有的策略临时获取
 */
public class ResumeReaderStrategy implements IResumeReaderStrategy {

	private IResumeReader m_ResumeReader;
	private String filepath;
	private String groupname;
	
	private byte[] fileBytes;
	
	public ResumeReaderStrategy(){}
	
	public ResumeReaderStrategy(String groupname){
		this.m_ResumeReader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
	}
	
	@Override
	public void readResume(String filepath){
		this.filepath = filepath;
		fileBytes = m_ResumeReader.readResume(this.filepath, groupname);
	}
	
	@Override
	public void readResume(String groupname, String filepath){
		setGroupname(groupname);
		readResume(filepath);
	}
	
	@Override
	public void setGroupname(String groupname) {
		this.groupname = groupname;
		this.m_ResumeReader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
	}

	@Override
	public byte[] getOriFile(){
		return fileBytes;
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
