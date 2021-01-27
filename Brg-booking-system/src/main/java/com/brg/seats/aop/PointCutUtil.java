package com.brg.seats.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutUtil {
	
	@Pointcut("execution(* com.brg.seats.dao.*.*(..))")
	public void foDaoPackage() {}
	
	@Pointcut("execution(* com.brg.seats.controller.*.*(..))")
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.brg.seats.service.*.*(..))")
	public void forServicePackage() {}
	
	@Pointcut("foDaoPackage() || forControllerPackage() || forServicePackage())")
	public void forDaoControllerServicePackage() {}

}
