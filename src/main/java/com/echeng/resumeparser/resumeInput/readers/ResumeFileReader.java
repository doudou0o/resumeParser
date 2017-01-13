package com.echeng.resumeparser.resumeInput.readers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ResumeFileReader extends ResumeReader {

	public byte[] readResume(String filepath, String groupname) {
		File file = new File(filepath);
		FileInputStream fin = openInputStream(file);
		if (fin == null)
			return null;
		return readInputStream(fin);
	}
	
	private byte[] readInputStream(FileInputStream fin){
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] tempData = new byte[4096];
			int count;
			while ((count = fin.read(tempData)) != -1) {
				os.write(tempData, 0, count);
			}
			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private FileInputStream openInputStream(File file){
		try {
			return FileUtils.openInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}


}
