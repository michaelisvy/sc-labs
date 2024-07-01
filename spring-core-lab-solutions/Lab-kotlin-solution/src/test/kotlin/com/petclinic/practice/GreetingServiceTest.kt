package com.petclinic.practice

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GreetingServiceTest(@Autowired val greetingService: GreetingService, @Autowired val greetingService2: GreetingService) {


    @Test
    fun shouldSayHi() {
        val greeting = Greeting("Michael")
        assertThat(greetingService.sayHi(greeting)).isEqualTo("hello Michael")
    }

    @Test
    fun shouldUseSingletons() {
        assertThat(greetingService).isEqualTo(greetingService2)
    }
}