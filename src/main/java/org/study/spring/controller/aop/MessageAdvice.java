package org.study.spring.controller.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageAdvice {
	private static final Logger logger = LoggerFactory.getLogger(MessageAdvice.class);
	
	@Before("execution(*"
			+"org.study.spring.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("핵심 업무 코드 정보 : "+ jp.getSignature());
		logger.info("method : "+ jp.getSignature().getName());
		logger.info("매개변수 : "+ Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(*"
			+"org.study.spring.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName()+":"+(endTime - startTime));
		logger.info("================================");
		return result;
	}
	
	
}
