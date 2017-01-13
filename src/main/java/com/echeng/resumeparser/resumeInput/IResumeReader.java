package com.echeng.resumeparser.resumeInput;

import java.io.File;

public interface IResumeReader {
	public byte[] readResume(File file);
	public byte[] readResume(String filepath);
	public byte[] readResume(String filepath, String groupname);
}
