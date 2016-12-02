package com.echeng.resumeparser.resumeInput;

public enum ResumeInputType {
	LOCAL, HTTP, DFS, DB, UNKNOW;
	
	public static ResumeInputType getResumeInputType(String groupname) {
		if (groupname == null)
			return ResumeInputType.UNKNOW;
		if ("local".equals(groupname.toLowerCase()))
			return ResumeInputType.LOCAL;
		if ("wget".equals(groupname.toLowerCase()))
			return ResumeInputType.HTTP;
		if (groupname.toLowerCase().startsWith("group"))
			return ResumeInputType.DFS;
		return ResumeInputType.UNKNOW;
	}
}
