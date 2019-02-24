package org.study.spring.controller.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Spring bean ����
@Aspect // AOP bean ����
public class LogCollector {
	private static final Logger logger = LoggerFactory.getLogger(LogCollector.class);
	
	@Around("execution(* org.study.spring.controller..*Controller.*(..))"
			+ "or execution(* org.study.spring.service..*Impl.*(..))"
			+ "or execution(* org.study.spring.model..dao.*Impl.*(..))")
	public Object logExecute(ProceedingJoinPoint jp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = jp.proceed(); // proceed()�� �������� proceed() ���� ȣ�� ��(stratTime)�� ����ǰ� �Ʒ� �ڵ���� ����ȴ�.
		// CLASS
		String type = jp.getSignature().getDeclaringTypeName();
		String name = "";
		if (type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl \t: ";
		} else if (type.indexOf("DAO") > -1) {
			name = "DAOImpl \t: ";
		}
		// METHOD
		logger.info(name + type + "." + jp.getSignature().getName() + "()");
		// Parameter
		logger.info(Arrays.toString(jp.getArgs()));
		// Main Logic
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		logger.info("���� �� �ð� : " + time);
		return result;
	}
}
