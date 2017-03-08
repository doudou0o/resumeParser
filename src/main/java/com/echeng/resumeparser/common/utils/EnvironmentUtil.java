package com.echeng.resumeparser.common.utils;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.common.utils.gearmanTools.GMDNSConfFileUtil;

import lombok.Data;

@Data
public class EnvironmentUtil {
	private static final Logger logger = LoggerFactory.getLogger(EnvironmentUtil.class);
	
	private static Environment curEnv;
	
	public enum Environment {
		ONLINE, DEV, TEST, UNKNOWN
	};


	public static Environment getCurrentEnvironment(){
		if (curEnv != null) return curEnv;

		if (judgeOS()) return curEnv;
		
		if (judgeGMconf()) return curEnv;
		
		if (judgeIPAddress()) return curEnv;
		
		curEnv = Environment.UNKNOWN;
		return curEnv;
	}
	
	public static String getConfigName(String fileName) {
		String retFileName = fileName;
		Environment env = EnvironmentUtil.getCurrentEnvironment();
		
		switch (env) {
			case UNKNOWN:
			case ONLINE:
				break;
			case DEV:
				retFileName += ".dev";
				break;
			case TEST:
				retFileName += ".test";
				break;
		}
		
		File file = new File(retFileName);
		if (!file.exists()) {
			logger.info("file %s is not exist, use config file %s.",retFileName, fileName);
			retFileName = fileName;
		}
		logger.info("current env is: %s, configName is %s",env.name(), retFileName);
		return retFileName;
	}
	
	/**
	 * 加载日志文件的时候，因为此函数使用logger进行输出
	 * @param fileName
	 * @return
	 */
	public static String getConfigNameForLogSetting(String fileName) {
		String retFileName = fileName;
		String os = System.getProperty("os.name"); 
		Environment env = Environment.ONLINE;
		
		//开发的时候打印到console中，否则开发的人员比较容易修改上线使用的config/log4j.properties
		if(os.toLowerCase().startsWith("windows") || os.toLowerCase().startsWith("mac")) {
			env = Environment.DEV;
		}
		
		switch (env) {
			case UNKNOWN:
			case ONLINE:
				break;
			case DEV:
				retFileName += ".dev";
				break;
			case TEST:
				retFileName += ".test";
				break;
		}
		
		File file = new File(retFileName);
		if (!file.exists()) {
			System.err.printf("file %s is not exist, use config file %s.",retFileName, fileName);
			retFileName = fileName;
		}
		return retFileName;
	}
	
	private static Boolean judgeOS(){
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("windows") || os.toLowerCase().startsWith("mac")){
			curEnv = Environment.DEV;
			return true;
		}
		else
			return false;
	}
	
	private static Boolean judgeGMconf(){
		List<String> allHosts = GMDNSConfFileUtil.getAllHosts();
		if (null == allHosts)
			return false;
		
		for (String host : allHosts){
			if (host.startsWith("192.168.8.")){
				curEnv = Environment.ONLINE;
				return true;
			} else if (host.startsWith("10.9.10.")) {
				curEnv = Environment.TEST;
				return true;
			} else if (host.startsWith("192.168.1.")) {
				curEnv = Environment.DEV;
				return true;
			} else {
				logger.info("no host can be matched:%s", allHosts.toString());
				return false;
			}
		}
		return false;
	}
	
	private static Boolean judgeIPAddress(){
		ArrayList<String> localIPs = new ArrayList<>();
		try {
			Enumeration<?> e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration<?> ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = (InetAddress) ee.nextElement();
					if (i.getHostAddress().trim().startsWith("192.168.1"))
						localIPs.add(i.getHostAddress().trim());
					if (i.getHostAddress().trim().startsWith("192.168.8"))
						localIPs.add(i.getHostAddress().trim());
					if (i.getHostAddress().trim().startsWith("10.9.10"))
						localIPs.add(i.getHostAddress().trim());
				}
			}
		} catch (SocketException e1) {
			logger.info("get local ip address raise some exceptions:\n", e1);
		}
		
		for (String ip : localIPs)
			if (ip.startsWith("192.168.8.")){
				curEnv = Environment.ONLINE;
				return true;
			}
		for (String ip : localIPs)
			if (ip.startsWith("10.9.10.")){
				curEnv = Environment.TEST;
				return true;
			}
		for (String ip : localIPs)
			if (ip.startsWith("192.168.1.")){
				curEnv = Environment.DEV;
				return true;
			}
		
		return false;
	}

	
	public static void main(String[] args) {
		System.out.println(EnvironmentUtil.getCurrentEnvironment());
	}
}
