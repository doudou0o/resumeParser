package com.echeng.resumeparser.model.divide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.echeng.resumeparser.common.utils.StringUtil;

public class RegularDivider implements IDivider {

	private Map<String, Integer> m_headlinesMap;
	private Boolean withoutOriHeadlines;

	private List<String> result;

	public RegularDivider() {
		m_headlinesMap = new HashMap<String, Integer>();
		withoutOriHeadlines = false;
		result = new ArrayList<String>();
	}


	public void setHeadlines(Map<String, Integer> headlinesMap) {
		if ( null != headlinesMap )
			this.m_headlinesMap = headlinesMap;
	}

	public void setWithoutOriHeadlines(Boolean withoutOriHeadlines) {
		this.withoutOriHeadlines = withoutOriHeadlines;
	}

	@Override
	public List<String> getResult() {
		return result;
	}

	@Override
	public void divide(String content) {
		List<String> lines = Arrays.asList(content.split("\n"));
		lines = StringUtil.trimList(lines);
		List<String> newLines = new ArrayList<String>(lines.size()+10);
		List<String[]> threeLines = StringUtil.travelListWithThreeLines(lines);
		
		for (String[] str3Item : threeLines) {
			newLines.addAll(handleHeadLine(str3Item));
		}
		result = newLines;
	}

	private List<String> handleHeadLine(String[] str3line){
		String lastline = str3line[0];
		String curline  = str3line[1];
		String nextline = str3line[2];

		// 标准标题：标题占一行
		if (isHeadLine(curline) && !isNagtiveLastline(lastline) && !isHeadLine(nextline)) {
			return Arrays.asList(RegularDividerHelper.generateNormalHeadLine(getHandlineId(curline)), curline);
		}

		// 非标准标题：标题在行首
		if (isSpecificHeadLine(curline, 0)!=null && !isNagtiveLastline(lastline) && !isHeadLine(nextline)) {
			String[] newlines = new String[3];
			String headline = isSpecificHeadLine(curline, 0);
			newlines[0] = RegularDividerHelper.generateNormalHeadLine(getHandlineId(headline));
			newlines[1] = headline;
			newlines[2] = curline.substring(curline.indexOf(headline)+curline.length());
			return Arrays.asList(newlines);
		}

		// 非标准标题：标题在行尾
		if (isSpecificHeadLine(curline, 1)!=null && !isNagtiveLastline(curline) && !isHeadLine(nextline)) {
			String[] newlines = new String[3];
			String headline = isSpecificHeadLine(curline, 1);
			newlines[0] = curline.substring(0, curline.indexOf(headline));
			newlines[1] = RegularDividerHelper.generateNormalHeadLine(getHandlineId(headline));
			newlines[2] = headline;
			return Arrays.asList(newlines);
		}

		return Arrays.asList(curline);
	}




	private String isSpecificHeadLine(String line, int i) {
		/**
		 * i = 0检查行首, 1 检查行尾
		 */
		String cleanline = RegularDividerHelper.cleanCandHeadline(line);

		for (int j = 2; j <= 5; j++){
			if (j >= cleanline.length())
				break;
			if (isHeadLine(cleanline.substring(
					0 + i * (cleanline.length() - j),
					j + i * (cleanline.length() - j)
					)))
				return cleanline.substring(
						0 + i * (cleanline.length() - j),
						j + i * (cleanline.length() - j)
						);
		}

		return null;
	}


	private boolean isNagtiveLastline(String lastline) {
		return RegularDividerHelper.isNagtiveLastline(lastline);
	}

	private Integer getHandlineId(String line) {
		String cleanline = RegularDividerHelper.cleanCandHeadline(line);
		if (m_headlinesMap.containsKey(cleanline))
			return m_headlinesMap.get(cleanline);
		else
			return RegularDividerHelper.getHandlineId(cleanline);
	}

	private boolean isHeadLine(String line) {
		String cleanline = RegularDividerHelper.cleanCandHeadline(line);
		if (withoutOriHeadlines)
			return m_headlinesMap.containsKey(cleanline);
		else
			return m_headlinesMap.containsKey(cleanline) || RegularDividerHelper.isHeadLine(cleanline);
	}


}
