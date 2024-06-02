package com.petclinic.core;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class PetclinicExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(PetclinicExceptionHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Error " + HttpStatus.BAD_REQUEST + ". [Property " + ex.getPropertyName() + ", " +
                "value " + ex.getValue() + "] cannot be converted. Required type: " + ex.getRequiredType();
        logger.error("an error occurred", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleException(EntityNotFoundException ex) {
        logger.error("an error occurred", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        logger.error("an error occurred", ex);
        throw ex;
    }
}
