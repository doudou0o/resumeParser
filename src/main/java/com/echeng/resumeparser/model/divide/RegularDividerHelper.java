package com.echeng.resumeparser.model.divide;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.echeng.resumeparser.common.utils.FileUtil;
import com.echeng.resumeparser.common.utils.StringUtil;


/*
 * # 0 基本信息, 1 联系方式, 2 期望信息, 3 教育经历,
 * # 4 工作经历, 5 教育经历, 6 项目经历, 7 证书模块,
 * # 8 语言模块, 9 培训经历,
 * # 99 其他
 */

public class RegularDividerHelper {

	private static final String HeadLinesDictFILE = "src/main/resources/divide/headlines.dict";
	
	private static Map<String, Integer> headlinesMap;

	private static Map<Integer, String> headlinesMap_name;
	
	static{
		headlinesMap = new HashMap<String, Integer>();
		headlinesMap_name = new HashMap<Integer, String>();
		loadBasicHeadlinesDict();
	}
	
	public static Integer getHandlineId(String word){
		if (word.length()<1 || !headlinesMap.containsKey(word))
			return -1;
		else
			return headlinesMap.getOrDefault(word, -1);
	}
	
	public static String cleanCandHeadline(String line) {
		String newStr = Pattern.compile("[^\u4e00-\u9fa5]").matcher(line).replaceAll("");
		if (newStr.length()==0) {//纯英文
			newStr = Pattern.compile("[^a-zA-Z\\s]").matcher(line).replaceAll("").trim();
		}
		newStr = Pattern.compile("[一二三四五六七八九十]").matcher(newStr).replaceAll("");
		return newStr;
	}

	public static boolean isHeadLine(String line) {
		return headlinesMap.containsKey(line);
	}

	public static String generateNormalHeadLine(String line, Integer... ids) {
		return String.format("====%s====+++%s+++", StringUtil.join(",", ids), line);
	}

	public static boolean isNagtiveLastline(String line) {
		return Pattern.compile("(所属行业|行业类别).{0,2}$").matcher(line).find();
	}








	private static void loadBasicHeadlinesDict(){
		try {
			List<String> lines = FileUtil.readLines(new File(HeadLinesDictFILE));
			for (String line : lines) {
				if (line.startsWith("#")) continue;
				String[] items = line.split(",");
				if (items.length!=3) continue;
				Integer id = Integer.parseInt(items[0]);
				headlinesMap_name.put(id, items[1].trim());
				
				List<String> words = Arrays.asList(items[2].split(";"));
				words.forEach(e -> headlinesMap.put(e.trim(), id));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





}
