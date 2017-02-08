package com.echeng.resumeparser.resumeInput;

import com.echeng.resumeparser.resumeInput.readers.ResumeDfsReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeFileReader;
import com.echeng.resumeparser.resumeInput.readers.ResumeHttpReader;

/**
 * 策略模式
 * 所有的策略临时获取
 */
public class ResumeReaderStrategy implements IResumeReaderStrategy {


	private String default_groupname;

	public ResumeReaderStrategy(){
		this.default_groupname = "local";
	}
	
	public ResumeReaderStrategy(String groupname){
		this.default_groupname = groupname;
	}
	
	@Override
	public byte[] readResume(String filepath){
		return readResume(this.default_groupname, filepath);
	}
	
	@Override
	public byte[] readResume(String groupname, String filepath){
		IResumeReader reader = getInstanceResumeReader(ResumeInputType.getResumeInputType(groupname));
		return reader.readResume(filepath, groupname);
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
