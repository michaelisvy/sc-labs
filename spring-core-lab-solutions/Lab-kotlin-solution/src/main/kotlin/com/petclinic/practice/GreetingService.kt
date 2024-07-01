package com.petclinic.practice

import org.springframework.stereotype.Service

@Service
class GreetingService {
    fun sayHi(greeting: Greeting): String {
        return "hello ${greeting.message}"
    }
}