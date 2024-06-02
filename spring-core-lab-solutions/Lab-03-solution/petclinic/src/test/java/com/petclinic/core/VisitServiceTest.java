package com.petclinic.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class VisitServiceTest {
    private final VisitService visitService;

    private final ApplicationContext applicationContext;

    @Autowired
    public VisitServiceTest(VisitService visitService, ApplicationContext applicationContext) {
        this.visitService = visitService;
        this.applicationContext = applicationContext;
    }

    @Test
    public void shouldFindVisit() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

    @Test
    public void shouldDisplayBeanNumber() {
        System.out.println(this.applicationContext.getBeanDefinitionCount());
    }
}
