package com.echeng.resumeparser.resumeInput;

import javax.annotation.Resource;


/**
 * 策略模式
 * 所有的策略临时获取
 */
public class ResumeReaderStrategy2 implements IResumeReaderStrategy {

	private String default_groupname;
	
//	@Autowired //由于Resource(j2ee提供)比Autowired(spring提供)更快
//	@Qualifier("fileReader")
	@Resource(name="fileReader")
	private IResumeReader resumeFileReader;
	@Resource(name="httpReader")
	private IResumeReader resumeHttpReader;
	@Resource(name="dfsReader")
	private IResumeReader resumeDfsReader;
	
	public ResumeReaderStrategy2(){
		this.default_groupname = "local";
	}

	public ResumeReaderStrategy2(String groupname){
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
