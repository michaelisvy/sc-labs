package com.petclinic.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/owner/{id}")
    public Owner findById(@PathVariable Long id) {
        return this.ownerService.findById(id);
    }

    @RequestMapping("/owner/search")
    public Owner findByFirstName(@RequestParam String firstName) {
        return this.ownerService.findByFirstName(firstName);
    }

    @PostMapping("/owner")
    public ResponseEntity<Owner> save (@RequestBody Owner owner) {
        var savedOwner = this.ownerService.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }
}
