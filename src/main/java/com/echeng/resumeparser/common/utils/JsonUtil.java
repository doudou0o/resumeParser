package com.echeng.resumeparser.common.utils;

import com.echeng.resumeparser.domain.resume.Resume;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	private static Gson gson_sta = new Gson();
	private static Gson gson_cop = new Gson();
	
	static{
		gson_cop = new GsonBuilder()
		        // 序列化null
		        .serializeNulls()
		        // 设置日期时间格式，另有2个重载方法
		        // 在序列化和反序化时均生效
		        .setDateFormat("yyyy年MM月dd日")
		        // 禁此序列化内部类
		        .disableInnerClassSerialization()
		        // 生成不可执行的Json（多了 )]}' 这4个字符）
		        //.generateNonExecutableJson()
		        // 禁止转义html标签
		        .disableHtmlEscaping()
		        // 格式化输出
		        .setPrettyPrinting()
		        .create();
		gson_sta = new GsonBuilder()
				// 去除未被标记的字段
				.excludeFieldsWithoutExposeAnnotation()
				// 序列化null
		        .serializeNulls()
		        // 设置日期时间格式，另有2个重载方法
		        // 在序列化和反序化时均生效
		        .setDateFormat("yyyy年MM月dd日")
		        // 禁此序列化内部类
		        .disableInnerClassSerialization()
		        // 生成不可执行的Json（多了 )]}' 这4个字符）
		        //.generateNonExecutableJson()
		        // 禁止转义html标签
		        .disableHtmlEscaping()
		        // 格式化输出
		        .setPrettyPrinting()
		        .create();
	}
	
	public static Resume jsonToResume(String json){
		return JsonToResume(json);
	}
	
	public static String resumeToJson(Resume resume){
		return resumeToJson(resume, true);
	}
	
	public static String resumeToJson(Resume resume, Boolean isStandard){
		if (isStandard)
			return resumeToStandardJson(resume);
		else
			return resumeToCompleteJson(resume);
	}
	
	private static String resumeToStandardJson(Resume resume){
		return gson_sta.toJson(resume);
	}
	
	private static String resumeToCompleteJson(Resume resume){
		return gson_cop.toJson(resume);
	}
	
	private static Resume JsonToResume(String json){
		return gson_cop.fromJson(json, Resume.class);
	}
}
