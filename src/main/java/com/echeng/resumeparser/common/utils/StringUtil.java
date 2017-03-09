package com.echeng.resumeparser.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

	public static List<String[]> travelListWithThreeLines(List<String> lines){
		List<String[]> threeLines = new ArrayList<String[]>(lines.size());
		for (int i = 0; i < lines.size(); i++) {
			String[] t = new String[3];
			t[1] = i-1<0 ? "" : lines.get(i-1);
			t[2] = lines.get(i);
			t[3] = i+1>=lines.size() ? "" : lines.get(i+1);
			threeLines.add(t);
		}
		return threeLines;
	}

	public static List<String[]> travelListWithFiveLines(List<String> lines){
		List<String[]> fiveLines = new ArrayList<String[]>(lines.size());
		for (int i = 0; i < lines.size(); i++) {
			String[] t = new String[5];
			t[0] = i-2<0 ? "" : lines.get(i-2);
			t[1] = i-1<0 ? "" : lines.get(i-1);
			t[2] = lines.get(i);
			t[3] = i+1>=lines.size() ? "" : lines.get(i+1);
			t[4] = i+2>=lines.size() ? "" : lines.get(i+2);
			fiveLines.add(t);
		}
		return fiveLines;
	}

}
