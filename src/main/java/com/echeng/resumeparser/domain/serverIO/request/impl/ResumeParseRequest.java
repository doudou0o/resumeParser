package com.echeng.resumeparser.domain.serverIO.request.impl;

import java.util.Map;

import com.echeng.resumeparser.common.Constant;
import com.echeng.resumeparser.domain.serverIO.ConvertOption;
import com.echeng.resumeparser.domain.serverIO.MergeOption;
import com.echeng.resumeparser.domain.serverIO.ParseOption;
import com.echeng.resumeparser.domain.serverIO.request.IRequest;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/*
 * every request must implement two static functions
 * for check request input ( Map<String, Object> P key value pair )
 */
@Data
public class ResumeParseRequest implements IRequest {
	
	private static final String MNAME = Constant.M_RESUMEPARSE;
	
	// must implement
	public static Boolean isMethodMatch(String m){
		return MNAME.equals(m);
	}
	// must implement
	public static String getRequestMatchInfo(Map<String,Object> reqDicP){
		if (null == reqDicP)
			return "no request parameters";
		if (!reqDicP.containsKey("groupname") || "".equals(reqDicP.get("groupname")))
			return "no 'groupname' in request parameters";
		if (!reqDicP.containsKey("filename") || "".equals(reqDicP.get("filename")))
			return "no 'filename' in request parameters";
		return null;
	}
	

	// ================
	
	private Map<String,Object> m_reqDicP;
	
	@SerializedName("groupname")
	private String groupName;
	@SerializedName("filename")
	private String fileName;
	@SerializedName("resumename")
	private String resumeName;
	@SerializedName("filecontent")
	private byte[] fileContent;
	@SerializedName("option")
	private Options option;
	
	private ConvertOption ct_op;
	private ParseOption ps_op;
	private MergeOption mg_op;
	
	public ResumeParseRequest(){};
	

	@Override
	public void buildRequest(Map<String, Object> reqDict) {
		this.m_reqDicP = reqDict;
	}
	
	public ConvertOption getConvertOptions(){
		if (ct_op != null)  return ct_op;
		ct_op = new ConvertOption();
		if (this.option.getIsNeedFeature() != null)
			ct_op.setIsNeedFeature(this.option.getIsNeedFeature() != 0);
		
		return ct_op;
	}
	
	public ParseOption getParseOptions(){
		if (ps_op != null) return ps_op;
		ps_op = new ParseOption();
		if (this.option.getRunType() != null)
			ps_op.setRunType(this.option.getRunType());
		if (this.option.getConstraint_degree() != null)
			ps_op.setConstraint_degree(this.option.getConstraint_degree());
		if (this.option.getTimeOut() >= 0)
			ps_op.setTimeOut(this.option.getTimeOut());

		return ps_op;
	}
	
	public MergeOption getMergeOptions(){
		if (mg_op != null) return mg_op;
		mg_op = new MergeOption();
		if (this.option.getIsFilterName() != null)
			mg_op.setIsFilterName(this.option.getIsFilterName() != 0);

		return mg_op;
	}

	@Data
	class Options {
		// run type
		@SerializedName("run_type")
		private Integer runType = 0;
		
		// max time out -- means must return before timeOut is up
		@SerializedName("time_out")
		private long timeOut = 0;
		
		// need filter name or not (default is false)
		@SerializedName("is_filter_name")
		private Integer isFilterName = 0;
		
		// constraint_degree
		@SerializedName("constraint_degree")
		private Integer constraint_degree = 0;
		
		// need feature when convert
		@SerializedName("is_need_feature")
		private Integer isNeedFeature = 0;
		
		
		private static final int DEFAULT_RUNTYPE = 0;
		private static final long DEFAULT_TIMEOUT = 0;
		private static final int DEFAULT_ISFILTERNAME = 0;
		private static final int DEFAULT_CONSTRAINT_DEGREE = 0;
		private static final int DEFAULT_ISNEEDFEATURE = 0;
		
		public Integer getRunType(){
			if (runType == null)
				runType = DEFAULT_RUNTYPE;
			return runType;
		}
		
		public long getTimeOut(){
			if (timeOut < 0)
				timeOut = DEFAULT_TIMEOUT;
			return timeOut;
		}
		
		public Integer getIsFilterName(){
			if (isFilterName == null)
				isFilterName = DEFAULT_ISFILTERNAME;
			return isFilterName;
		}
		
		public Integer getConstraint_degree(){
			if (constraint_degree == null)
				constraint_degree = DEFAULT_CONSTRAINT_DEGREE;
			return constraint_degree;
		}
		
		public Integer getIsNeedFeature(){
			if (isNeedFeature == null)
				isNeedFeature = DEFAULT_ISNEEDFEATURE;
			return isNeedFeature;
		}
	}
}

