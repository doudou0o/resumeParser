package com.echeng.resumeparser.resumeInput;

import java.io.File;

public interface IResumeReader {
	public void readResume(File file);
	public void readResume(String filepath);
	public void readResume(String filepath, String groupname);
	public byte[] getResumeOriFile();
}
