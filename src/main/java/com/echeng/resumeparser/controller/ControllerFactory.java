package com.echeng.resumeparser.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.echeng.resumeparser.common.Constant;

public class ControllerFactory {
	
	@Autowired
	private ParseController parseController;
	
	public IController getController(String m){
		if (Constant.M_RESUMEPARSE.equals(m)){
			return parseController;
		}
		
		return null;
	}
}
