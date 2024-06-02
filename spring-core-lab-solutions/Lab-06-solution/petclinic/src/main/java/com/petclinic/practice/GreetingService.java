package com.petclinic.practice;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String sayHi() {
        return "Hello John";
    }
}
