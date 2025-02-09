package com.petclinic.core

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@RestController
@RequestMapping("/visit")
class VisitController(val visitService: VisitService) {

    @Operation(summary="find a Pet visit by its id")
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Visit {
        return visitService.findById(id)
    }

    @PostMapping("/")
    fun save(@RequestBody visit: Visit): ResponseEntity<Visit>  {
        val savedVisit = visitService.save(visit)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisit);
    }

    @ApiResponse(responseCode = "404", description = "Typically thrown when Invoice ID does not exist")
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error ${HttpStatus.NOT_FOUND}. Invoice does not exist")
    }

    @ApiResponse(responseCode = "400", description = "Typically used when Invoice ID is not a number")
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ${HttpStatus.BAD_REQUEST}. The visit ID could not be converted to a numeric value")
    }
}