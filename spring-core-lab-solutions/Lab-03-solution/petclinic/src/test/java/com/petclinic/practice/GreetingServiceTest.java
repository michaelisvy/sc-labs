package com.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GreetingServiceTest {
    private final ApplicationContext applicationContext;
    private final GreetingService greetingService;

    @Autowired
    public GreetingServiceTest(ApplicationContext applicationContext, GreetingService greetingService) {
        this.applicationContext = applicationContext;
        this.greetingService = greetingService;
    }

    @Test
    public void shouldGreetSuccessfully() {
        int beanDefinitionCount = this.applicationContext.getBeanDefinitionCount();
        assertThat(beanDefinitionCount).isGreaterThanOrEqualTo(3);
        System.out.println("number of bean definitions: " + beanDefinitionCount);
    }

    @Test
    public void shouldCountBeanDefinitions() {
        int beanDefinitionCount = this.applicationContext.getBeanDefinitionCount();
        assertThat(beanDefinitionCount).isGreaterThanOrEqualTo(3);
        System.out.println("number of bean definitions: " + beanDefinitionCount);
    }

    @Test
    public void shouldDisplayBeanDefinitions() {
        String[] beanNames = this.applicationContext.getBeanDefinitionNames();
        System.out.println("All beans in the application context:");
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
