package com.petclinic.core

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/owner")
class OwnerController(
    @Autowired val ownerService: OwnerService) {

    @Operation(summary = "search an owner by his/her first name")
    @GetMapping("/search")
    fun findByFirstName(@RequestParam firstName: String): Owner? {
        return ownerService.findByFirstName(firstName)
    }

    @GetMapping("/{id}")
    fun findVisit(@PathVariable id: Int): Owner {
        return ownerService.findById(id)
    }

    @PostMapping("/")
    fun save(@RequestBody owner: Owner): ResponseEntity<Owner> {
        val savedOwner = ownerService.save(owner)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNoSuchElementException(ex: EntityNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error ${HttpStatus.NOT_FOUND}. ${ex.message}")
    }
}