package com.nimap.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;




@Component
@Aspect
public class ExceptionControllerAdvice {
	public static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@AfterThrowing(pointcut = "execution(* com.nimap.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) throws Exception {
		LOGGER.error(exception.getMessage(), exception);
	}
	

}
