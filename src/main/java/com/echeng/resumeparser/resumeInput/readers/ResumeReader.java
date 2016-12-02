package com.echeng.resumeparser.resumeInput.readers;

import java.io.File;

import com.echeng.resumeparser.resumeInput.IResumeReader;

public abstract class ResumeReader implements IResumeReader {
	
	private byte[] oriFile;
	
	public void readResume(File file){
		readResume(file.getAbsolutePath());
	}
	
	public void readResume(String filepath){
		readResume(filepath, null);
	}
	
	public byte[] getResumeOriFile(){
		return oriFile;
	}

	public void setOriFile(byte[] oriFile){
		this.oriFile = oriFile;
	}

}
