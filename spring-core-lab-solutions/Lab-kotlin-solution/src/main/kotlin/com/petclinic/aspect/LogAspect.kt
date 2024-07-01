package com.petclinic.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect @Component //@Profile("monitoring")
class LogAspect {
    @Around("execution(* com..*Service.*(..))")
    fun logTimeSpent(joinPoint: ProceedingJoinPoint): Any? {
        val stopWatch: StopWatch = StopWatch()
        stopWatch.start()
        println("inside LogAspect")
        val result =  joinPoint.proceed()
        stopWatch.stop()
        println("function called: ${joinPoint.signature}: time spent: ${stopWatch.totalTimeMillis} ms")
        return result

        // Log with Trace id: "1234"
        // call to Service A via message queue. Header: "12334"
        // rest call to Service B. Http header contains trace id "1234"
        // receives response from Service B
        // logs with Trace id: "1234"

    }
}