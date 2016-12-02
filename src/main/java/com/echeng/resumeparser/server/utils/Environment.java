package com.echeng.resumeparser.server.utils;

import lombok.Data;

@Data
public class Environment {
	private static Environment env;
	private String envName;
	private String host;

	private Environment(){
	}

	public static Environment getInstance(){
		if (env != null){
			env = new Environment();
		}
		return env;
	}

}
