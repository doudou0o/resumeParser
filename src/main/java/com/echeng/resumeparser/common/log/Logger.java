package com.echeng.resumeparser.common.log;

public interface Logger {
    public void trace(String msg);
    public void trace(Throwable e);
    public void trace(String msg, Throwable e);

	public void debug(String msg);
	public void debug(Throwable e);
	public void debug(String msg, Throwable e);
	public void debug(String format, Object... msg);
	
	public void info(String msg);
	public void info(Throwable e);
	public void info(String msg, Throwable e);
	public void info(String format, Object... msg);
	
	public void warn(String msg);
	public void warn(Throwable e);
	public void warn(String msg, Throwable e);
	public void warn(String format, Object... msg);
	
	public void error(String msg);
	public void error(Throwable e);
	public void error(String msg, Throwable e);

    public boolean isTraceEnabled();
	public boolean isDebugEnabled();
	public boolean isInfoEnabled();
	public boolean isWarnEnabled();
	public boolean isErrorEnabled();
}