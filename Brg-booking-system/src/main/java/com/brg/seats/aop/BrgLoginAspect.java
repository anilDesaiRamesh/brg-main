package com.brg.seats.aop;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BrgLoginAspect {
	
	private static final Logger log = 	
			LoggerFactory.getLogger(BrgLoginAspect.class);

	@Around("com.brg.seats.aop.PointCutUtil.forDaoControllerServicePackage()")
	public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String methodSignature = proceedingJoinPoint.getSignature().toShortString();				
		// get begin timestamp		
		log.info("Started********* " + methodSignature + " time : " + LocalDateTime.now());
		// execute the method
		Object object = proceedingJoinPoint.proceed();
		// get end timestamp		
		log.info("Ended********* " + methodSignature + " time : " + LocalDateTime.now());
		// compute the duration & display
		return object;
	}
}
