package com.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GreetingServiceTest {
    private final GreetingService greetingService1;
    private final GreetingService greetingService2;

    @Autowired
    public GreetingServiceTest(GreetingService greetingService1, GreetingService greetingService2) {
        this.greetingService1 = greetingService1;
        this.greetingService2 = greetingService2;
    }

    @Test
    public void shouldGreetSuccessfully() {
        assertThat(this.greetingService1.sayHi()).isEqualTo("Hello John");
    }

    @Test
    public void shouldUseSingletons() {
        assertThat(this.greetingService1).isEqualTo(this.greetingService2);
    }
}
