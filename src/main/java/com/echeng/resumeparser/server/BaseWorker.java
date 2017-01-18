package com.echeng.resumeparser.server;

import org.apache.log4j.PropertyConfigurator;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.common.utils.config.GlobalConfig;
import com.echeng.resumeparser.common.utils.config.ALConfig;

public abstract class BaseWorker implements Server {
	static {
		PropertyConfigurator.configure(Constant.LOG_FILE);
	}
	
	public static final ALConfig SEVER_CONFIG = GlobalConfig.getInstanceFromFile(Constant.SERVER_FILE);

	public static final String FUNCTION_NAME = SEVER_CONFIG.getString("worker_name");
	public static final String SERVER_HOST = SEVER_CONFIG.getString("server_host");
	public static final String SERVER_PORT = SEVER_CONFIG.getString("server_port");
	public static final String WORKER_COUNT = SEVER_CONFIG.getString("worker_count");
	
	public static final ERROR ERROR_0 = ERROR.ERROR_0;
	public static final ERROR ERROR_1 = ERROR.ERROR_1;
	public static final ERROR ERROR_2 = ERROR.ERROR_2;
	public static final ERROR ERROR_3 = ERROR.ERROR_3;
	public static final ERROR ERROR_4 = ERROR.ERROR_4;
	public static final ERROR ERROR_5 = ERROR.ERROR_5;
	
	
	public abstract void init();
	
	public abstract void reload();

	public abstract void run();
	
	public abstract void destory();
	
} 

enum ERROR{
	ERROR_0(0,""),
	ERROR_1(1,""),
	ERROR_2(2,""),
	ERROR_3(3,""),
	ERROR_4(4,""),
	ERROR_5(5,""),
	;
	
	private int id;
	private String info;
	
	private ERROR(int id, String info){
		this.id   = id;
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
};