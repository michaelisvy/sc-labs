package com.petclinic.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect @Component
class LogAspect {

    private val log: Logger = LoggerFactory.getLogger(LogAspect::class.java)
    @Around("execution(* com..*Service.*(..))")
    fun logTimeSpent(joinPoint: ProceedingJoinPoint): Any? {
        val stopWatch: StopWatch = StopWatch()
        stopWatch.start()
        log.info("inside LogAspect")
        val result =  joinPoint.proceed()
        stopWatch.stop()
        log.info("function called: ${joinPoint.signature}: time spent: ${stopWatch.totalTimeMillis} ms")
        return result
    }
}