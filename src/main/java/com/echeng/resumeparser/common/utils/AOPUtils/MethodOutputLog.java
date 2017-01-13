package com.echeng.resumeparser.common.utils.AOPUtils;

import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodOutputLog implements MethodInterceptor {
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object object = invocation.proceed();
        // 方法名称
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();  
        String input = Arrays.toString(invocation.getArguments());
        String output = object.toString();
        return object;
	}

}
