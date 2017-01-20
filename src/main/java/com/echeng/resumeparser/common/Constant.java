package com.echeng.resumeparser.common;

import com.echeng.resumeparser.common.utils.config.ALConfig;
import com.echeng.resumeparser.common.utils.config.GlobalConfig;

public class Constant {

	private static ALConfig CONSTANT_CONF = GlobalConfig.getInstanceFromFile("constant.properties");

	// constant variable
	public static final String TXT  = "txt";
	public static final String PDF  = "pdf";
	public static final String DOC  = "doc";
	public static final String DOCX = "docx";
	public static final String HTML = "html";
	
	// resume_parse,name_filter
	public static final String M_RESUMEPARSE = "resume_parse";
	public static final String M_NAMEFILTER = "name_filter";

	
	// constant files
	public static final String SPRING_FILE = CONSTANT_CONF.getString("spring_context");
	public static final String SERVER_FILE = CONSTANT_CONF.getString("server_properties");
	public static final String LOG_FILE = CONSTANT_CONF.getString("log_properties");
	
}
