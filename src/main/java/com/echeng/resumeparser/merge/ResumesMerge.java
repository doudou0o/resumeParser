package com.echeng.resumeparser.merge;

import java.util.List;

import com.echeng.resumeparser.domain.resume.Resume;
import com.echeng.resumeparser.domain.serverIO.MergeOption;

public class ResumesMerge {

	public Resume merge(List<Resume> resumeList, MergeOption options){
		if (resumeList != null && resumeList.size() > 0)
			return resumeList.get(0);
		return null;
	}
}
