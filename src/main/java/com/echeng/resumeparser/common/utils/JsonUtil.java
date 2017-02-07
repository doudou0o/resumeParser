package com.echeng.resumeparser.common.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.echeng.resumeparser.domain.resume.Resume;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	//
	private static Gson gson_sta;
	private static Gson gson_cop;
	
	static{
		gson_cop = new GsonBuilder()
		        // 序列化null
		        .serializeNulls()
		        // 设置日期时间格式，在序列化和反序化时均生效
		        .setDateFormat("yyyy年MM月dd日")
		        // 禁此序列化内部类
		        .disableInnerClassSerialization()
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
		        // 设置日期时间格式，在序列化和反序化时均生效
		        .setDateFormat("yyyy年MM月dd日")
		        // 禁止转义html标签
		        .disableHtmlEscaping()
		        // 格式化输出
		        .setPrettyPrinting()
		        // 禁此序列化内部类
		        //.disableInnerClassSerialization()
		        // 生成不可执行的Json（会在最后多出 )]}' 这4个字符）
		        //.generateNonExecutableJson()
		        .create();
	}
	
	// ===========普通 包装================
    public static LinkedHashMap<String, Object> parseLinkedHashMap(Object obj) {
		return parseLinkedHashMap(obj, false);
    }
	@SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> parseLinkedHashMap(Object obj, Boolean isExposed) {
		if (isExposed)
			return (LinkedHashMap<String, Object>) gson_sta.fromJson(obj.toString(), LinkedHashMap.class);
		else
			return (LinkedHashMap<String, Object>) gson_cop.fromJson(obj.toString(), LinkedHashMap.class);
    }


	public static HashMap<String, Object> parseHashMap(Object obj) {
        return parseHashMap(obj, false);
    }
    @SuppressWarnings("unchecked")
	public static HashMap<String, Object> parseHashMap(Object obj, Boolean isExposed) {
    	if (isExposed)
			return (HashMap<String, Object>) gson_sta.fromJson(obj.toString(), HashMap.class);
    	else
			return (HashMap<String, Object>) gson_cop.fromJson(obj.toString(), HashMap.class);
    }


    public static String toJson(Object obj) {
        return toJson(obj, false);
    }
    public static String toJson(Object obj, Boolean isExposed) {
    	if (isExposed)
			return gson_sta.toJson(obj);
    	else
			return gson_cop.toJson(obj);
    }


    public static <T> T fromJson(String jsonStr, Class<T> classOfT) {
        return fromJson(jsonStr, classOfT, false);
    }
    public static <T> T fromJson(String jsonStr, Class<T> classOfT, Boolean isExposed) {
    	if (isExposed)
			return gson_sta.fromJson(jsonStr, classOfT);
    	else
			return gson_cop.fromJson(jsonStr, classOfT);
    }


    public static Object fromJson(String jsonStr, Type type) {
        return fromJson(jsonStr, type, false);
    }
    public static Object fromJson(String jsonStr, Type type, Boolean isExposed) {
    	if (isExposed)
			return gson_sta.fromJson(jsonStr, type);
    	else
			return gson_cop.fromJson(jsonStr, type);
    }


	// ========resume 相关========
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
