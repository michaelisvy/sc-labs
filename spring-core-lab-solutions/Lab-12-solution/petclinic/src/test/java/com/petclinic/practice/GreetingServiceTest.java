package com.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class GreetingServiceTest {
    private final ApplicationContext applicationContext;
    private final GreetingService greetingService;

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceTest.class);

    @Autowired
    public GreetingServiceTest() {
        this.applicationContext = new AnnotationConfigApplicationContext("com.petclinic.practice");
        this.greetingService = this.applicationContext.getBean(GreetingService.class);

    }

    @Test
    public void shouldGreetSuccessfully() {
        assertThat(this.greetingService.sayHi()).isEqualTo("Hello John");
    }

    @Test
    public void shouldCountBeanDefinitions() {
        int beanDefinitionCount = this.applicationContext.getBeanDefinitionCount();
        assertThat(beanDefinitionCount).isGreaterThanOrEqualTo(3);
        logger.info("number of bean definitions: {}", beanDefinitionCount);
    }

    @Test
    public void shouldDisplayBeanDefinitions() {
        String[] beanNames = this.applicationContext.getBeanDefinitionNames();
        logger.info("All beans in the application context:");
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    }
}
