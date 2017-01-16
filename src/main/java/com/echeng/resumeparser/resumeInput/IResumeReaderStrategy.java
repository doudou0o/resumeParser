package com.echeng.resumeparser.resumeInput;

public interface IResumeReaderStrategy {
	public void readResume(String filepath);
	public void readResume(String groupname, String filepath);
	public byte[] getOriFile();
	public void setGroupname(String groupname);
}
