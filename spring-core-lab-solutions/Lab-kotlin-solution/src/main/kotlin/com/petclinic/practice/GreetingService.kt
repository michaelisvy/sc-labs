package com.petclinic.practice

import org.springframework.stereotype.Service

@Service //@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class GreetingService {
    fun sayHi(greeting: Greeting): String {
        return "hello ${greeting.message}"
    }
}