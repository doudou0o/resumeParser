package com.echeng.resumeparser.model;

import java.util.List;
import java.util.Map;

import com.echeng.resumeparser.model.divide.RegularDivider;

/**
 * the model for dividing the content
 * 
 * @return the List<String> after dividing.
 * Each String is a block that begin with "===id,id...===+++name,name...+++\n"
 * Each String is segmented by "\n"
 *
 */
public class DivideModel {

	public static List<String> divide(String content) {
		return divide(content, false, null);
	}
	
	public static List<String> divide(String content, Map<String, Integer> headlinesMap) {
		return divide(content, false, headlinesMap);
	}
	
	public static List<String> divide(String content, Boolean without, Map<String, Integer> headlinesMap) {
		//TODO other dividers such as CRF
		RegularDivider regDiv = new RegularDivider();
		regDiv.setHeadlines(headlinesMap);
		regDiv.setWithoutOriHeadlines(without);
		regDiv.divide(content);
		List<String> regDivRet = regDiv.getResult();
		
		return regDivRet;
	}
	

}
