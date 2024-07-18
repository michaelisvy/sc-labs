package com.petclinic.practice

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class GreetingServiceTest @Autowired constructor(val applicationContext: ApplicationContext, val greetingService: GreetingService) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun shouldSayHi() {
        val greeting = Greeting("Michael")
        assertThat(greetingService.sayHi(greeting)).isEqualTo("hello Michael")
    }

    @Test
    fun shouldCountBeanDefinitions() {
        val beanDefinitionCount: Int = this.applicationContext.beanDefinitionCount
        assertThat(beanDefinitionCount).isGreaterThanOrEqualTo(3)
        logger.info("number of bean definitions: {}", beanDefinitionCount)
    }

    @Test
    fun shouldDisplayBeanDefinitions() {
        val beanNames = this.applicationContext.beanDefinitionNames
        logger.info("All beans in the application context:")
        for (beanName in beanNames) {
            logger.info(beanName)
        }
    }
}