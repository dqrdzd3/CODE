package com.hwsensor.framework.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAdvice {
	
	@After(value="com.hwsensor.framework.advice.LogAdvice.doAddMethod()")
	public void test(JoinPoint jp) throws Exception{
		//System.out.println("执行执行！！！！！！");
		System.out.println(jp.getTarget().getClass().getName());
	}
	
	@Pointcut("execution(* doAdd*(..))")
	public void doAddMethod(){
		
	}
}
