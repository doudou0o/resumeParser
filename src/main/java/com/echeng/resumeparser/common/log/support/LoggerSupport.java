
package com.echeng.resumeparser.common.log.support;

import com.echeng.resumeparser.common.log.Logger;

/**
 * 作为logger 类的support类使用。
 *
 */
public class LoggerSupport implements Logger {

	private Logger logger;

	public LoggerSupport(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private String addExtraInfo(String msg) {
	    return msg;
	}

    public void trace(String msg, Throwable e) {
        try {
            logger.trace(addExtraInfo(msg), e);
        } catch (Throwable t) {
        }
    }

    public void trace(Throwable e) {
        try {
            logger.trace(e);
        } catch (Throwable t) {
        }
    }

    public void trace(String msg) {
        try {
            logger.trace(addExtraInfo(msg));
        } catch (Throwable t) {
        }
    }

	public void debug(String msg, Throwable e) {
		try {
			logger.debug(addExtraInfo(msg), e);
		} catch (Throwable t) {
		}
	}

    public void debug(Throwable e) {
        try {
            logger.debug(e);
        } catch (Throwable t) {
        }
    }

	public void debug(String msg) {
		try {
			logger.debug(addExtraInfo(msg));
		} catch (Throwable t) {
		}
	}

	public void debug(String format, Object... msg) {
		try {
			if(this.isDebugEnabled()) {
				String tmp = String.format(format, msg);
				logger.debug(addExtraInfo(tmp));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void info(String msg, Throwable e) {
		try {
			logger.info(addExtraInfo(msg), e);
		} catch (Throwable t) {
		}
	}

	public void info(String msg) {
		try {
			logger.info(addExtraInfo(msg));
		} catch (Throwable t) {
		}
	}
	public void info(String format, Object... msg) {
		try {
			if(this.isInfoEnabled()) {
				String tmp = String.format(format, msg);
				logger.info(addExtraInfo(tmp));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void warn(String msg, Throwable e) {
		try {
			logger.warn(addExtraInfo(msg), e);
		} catch (Throwable t) {
		}
	}

	public void warn(String msg) {
		try {
			logger.warn(addExtraInfo(msg));
		} catch (Throwable t) {
		}
	}
	public void warn(String format, Object... msg) {
		try {
			if(this.isWarnEnabled()) {
				String tmp = String.format(format, msg);
				logger.warn(addExtraInfo(tmp));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void error(String msg, Throwable e) {
		try {
			logger.error(addExtraInfo(msg), e);
		} catch (Throwable t) {
		}
	}

	public void error(String msg) {
		try {
			logger.error(addExtraInfo(msg));
		} catch (Throwable t) {
		}
	}

    public void error(Throwable e) {
        try {
            logger.error(e);
        } catch (Throwable t) {
        }
    }

    public void info(Throwable e) {
        try {
            logger.info(e);
        } catch (Throwable t) {
        }
    }

    public void warn(Throwable e) {
        try {
            logger.warn(e);
        } catch (Throwable t) {
        }
    }

    public boolean isTraceEnabled() {
        try {
            return logger.isTraceEnabled();
        } catch (Throwable t) {
            return false;
        }
    }

	public boolean isDebugEnabled() {
		try {
			return logger.isDebugEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean isInfoEnabled() {
		try {
			return logger.isInfoEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean isWarnEnabled() {
		try {
			return logger.isWarnEnabled();
		} catch (Throwable t) {
			return false;
		}
	}
	
	public boolean isErrorEnabled() {
	    try {
	        return logger.isErrorEnabled();
	    } catch (Throwable t) {
	        return false;
	    }
	}

}