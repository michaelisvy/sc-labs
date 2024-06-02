package com.petclinic.tech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private final Logger log = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log")
    public void log() {
        log.debug("this is a debug message");
        log.info("this is an info");
        log.warn("this is a warning");
        log.error("this is an error");
    }
}
