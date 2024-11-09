package com.petclinic.tech;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Aspect @Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.petclinic..*Service.*(..))")
    public void logMethodsCalledBefore( JoinPoint joinPoint ) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        logger.info("Before executing method: {}.{}", className, methodName);
    }

    @Around("execution(* com.petclinic..*Service.*(..))")
    public Object logMethodsCalled( ProceedingJoinPoint joinPoint ) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        StopWatch stopWatch =  new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();
        stopWatch.stop();
        logger.info("time spent: {} ms", stopWatch.getTotalTime(TimeUnit.MILLISECONDS));
        return result;
    }

}
