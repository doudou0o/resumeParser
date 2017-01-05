
package com.echeng.resumeparser.common.log;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.echeng.resumeparser.common.log.log4j.Log4jLoggerAdapter;
import com.echeng.resumeparser.common.log.support.LoggerSupport;


public class LoggerFactory {

	private LoggerFactory() {
	}

	private static volatile LoggerAdapter LOGGER_ADAPTER;
	
	private static final ConcurrentMap<String, LoggerSupport> LOGGERS = new ConcurrentHashMap<String, LoggerSupport>();

	// 查找常用的日志框架
	static {
		setLoggerAdapter(new Log4jLoggerAdapter());
	}

	/**
	 * 设置日志输出器供给器
	 * 
	 * @param loggerAdapter
	 *            日志输出器供给器
	 */
	public static void setLoggerAdapter(LoggerAdapter loggerAdapter) {
		if (loggerAdapter != null) {
			Logger logger = loggerAdapter.getLogger(LoggerFactory.class.getName());
			logger.info("using logger: " + loggerAdapter.getClass().getName());
			LoggerFactory.LOGGER_ADAPTER = loggerAdapter;
			for (Map.Entry<String, LoggerSupport> entry : LOGGERS.entrySet()) {
				entry.getValue().setLogger(LOGGER_ADAPTER.getLogger(entry.getKey()));
			}
		}
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key
	 *            分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(Class<?> key) {
		LoggerSupport logger = LOGGERS.get(key.getName());
		if (logger == null) {
			LOGGERS.putIfAbsent(key.getName(), new LoggerSupport(LOGGER_ADAPTER.getLogger(key)));
			logger = LOGGERS.get(key.getName());
		}
		return logger;
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key
	 *            分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(String key) {
		LoggerSupport logger = LOGGERS.get(key);
		if (logger == null) {
			LOGGERS.putIfAbsent(key, new LoggerSupport(LOGGER_ADAPTER.getLogger(key)));
			logger = LOGGERS.get(key);
		}
		return logger;
	}
	
	public static void setLevel(Level level) {
		LOGGER_ADAPTER.setLevel(level);
	}

	public static Level getLevel() {
		return LOGGER_ADAPTER.getLevel();
	}
	
	public static File getFile() {
		return LOGGER_ADAPTER.getFile();
	}

}