package com.echeng.resumeparser.resumeInput;

public interface IResumeReaderStrategy {
	public byte[] readResume(String filepath);
	public byte[] readResume(String groupname, String filepath);
}
