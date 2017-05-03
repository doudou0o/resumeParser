package com.echeng.resumeparser.server;

import java.util.Arrays;
import java.util.List;

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
	public static final Integer SERVER_PORT = SEVER_CONFIG.getInt("server_port");
	public static final Integer WORKER_COUNT = SEVER_CONFIG.getInt("worker_count");

	public static final List<String> M_LIST = Arrays.asList(SEVER_CONFIG.getString("worker_mList").split(","));
	
	public static final ERROR ERROR_0 = ERROR.ERROR_0;
	public static final ERROR ERROR_1 = ERROR.ERROR_1;
	public static final ERROR ERROR_2 = ERROR.ERROR_2;
	public static final ERROR ERROR_3 = ERROR.ERROR_3;
	public static final ERROR ERROR_4 = ERROR.ERROR_4;
	public static final ERROR ERROR_5 = ERROR.ERROR_5;
	public static final ERROR ERROR_6 = ERROR.ERROR_6;
	public static final ERROR ERROR_7 = ERROR.ERROR_7;
	
	
	public abstract void init();
	
	public abstract void reload();

	public abstract void run();
	
	public abstract void destory();
	
	public enum ERROR{
		ERROR_0(0,""),
		ERROR_1(1001,"can not unpack gm request!"),
		ERROR_2(1002,"module in request is not exist!"),
		ERROR_3(1003,"no header or header is not exist!"),
		ERROR_4(1004,"request(p) is invalid!"),
		ERROR_5(1005,"request is not satisfied with structure of c,m,p"),
		ERROR_6(1006,"raise some unknow Exceptions"),
		ERROR_7(1007,"parse failed"),
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
} 
