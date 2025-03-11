package com.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GreetingServiceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private GreetingService greetingService;

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceTest.class);

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
