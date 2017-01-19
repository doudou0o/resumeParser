package com.echeng.resumeparser.server;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.echeng.resumeparser.common.Constant;

public class ServiceMain {
	
	public static void main(String[] args) {

		String contextFile = Constant.SPRING_FILE;

		if (args.length < 1) {
			File file = new File(contextFile);
			if (!file.exists()) {
				System.err.println("service xml file is not exist.");
				System.exit(-1);
			}
		} else {
			contextFile = args[0];
			File file = new File(contextFile);
			if (!file.exists()) {
				System.err.println("service xml file is not exist.");
				System.exit(-1);
			}
		}

		try {		
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(contextFile);
			Server obj = (Server) context.getBean("server");
			obj.run();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}
}
