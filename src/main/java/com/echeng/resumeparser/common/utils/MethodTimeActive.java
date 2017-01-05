package com.echeng.resumeparser.common.utils;

import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;



public class MethodTimeActive implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(MethodTimeActive.class);
	
	
	@SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch watch = new StopWatch();
        // 计时器开始
        watch.start(); 
		Object object = invocation.proceed();
		watch.stop();
        // 方法名称
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();  
        // 获取计时器计时时间
        String args = "";
        if("candidateFunctionScore".equals(methodName)){
        	Set<Object> a = (Set<Object>)invocation.getArguments()[0];
        	args = "loop Count "+ a.size();
        }
        
        return object;
	}
	

}
