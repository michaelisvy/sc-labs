package com.petclinic.practice

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LogTest {
    private val log: Logger = LoggerFactory.getLogger(LogTest::class.java)

    @Test
    fun shouldUseMultipleLoggingLevels(){
        log.trace("this is a trace message")
        log.debug("this is a debug message")
        log.info("this is an info message")
        log.warn("this is a warn message")
        log.error("this is an error message")
    }
}