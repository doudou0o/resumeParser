package com.echeng.resumeparser.resumeInput.readers;

import java.io.File;

import com.echeng.resumeparser.resumeInput.IResumeReader;

public abstract class ResumeReader implements IResumeReader {
	
	public byte[] readResume(File file){
		return readResume(file.getAbsolutePath());
	}
	
	public byte[] readResume(String filepath){
		return readResume(filepath, null);
	}

}