
package com.echeng.resumeparser.common.log;

import java.io.File;

public interface LoggerAdapter {
	Logger getLogger(Class<?> key);
	Logger getLogger(String key);
	
	void setLevel(Level level);
	Level getLevel();
	
	/**
	 * 获得日志文件
	 * @return
	 */
	File getFile();
	void setFile(File file);

}