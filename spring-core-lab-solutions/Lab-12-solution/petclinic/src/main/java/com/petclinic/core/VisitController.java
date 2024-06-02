package com.petclinic.core;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {
    private final VisitService  visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping("/visit/{id}")
    public Visit findById(@PathVariable int id) {
        return this.visitService.findById(id);
    }
}
