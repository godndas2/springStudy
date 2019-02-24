package org.study.spring.controller.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Spring bean 관리
@Aspect // AOP bean 관리
public class LogCollector {
	private static final Logger logger = LoggerFactory.getLogger(LogCollector.class);
	
	// 해당 하위 메소드들과 파라미터값을 모두 log로 보여줌
//	@Around("execution(* org.study.spring.controller..*Controller.*(..))"
//			+ "or execution(* org.study.spring.service..*Impl.*(..))"
//			+ "or execution(* org.study.spring.model..dao.*Impl.*(..))")
	public Object logExecute(ProceedingJoinPoint jp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = jp.proceed(); // proceed()를 기준으로 proceed() 위는 호출 전(stratTime)이 실행되고 아래 코드들이 실행된다.
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
		logger.info("실행 된 시간 : " + time);
		return result;
	}
}
