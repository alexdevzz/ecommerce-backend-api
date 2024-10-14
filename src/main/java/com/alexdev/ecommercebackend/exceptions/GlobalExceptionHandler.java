package com.alexdev.ecommercebackend.exceptions;

import com.alexdev.ecommercebackend.payload.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> mapErrors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();

            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            mapErrors.put(fieldName, message);
        });
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(mapErrors)
                .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoSuchElementException e) {

        Map<String, String> mapErrors = new HashMap<>();

        mapErrors.put("error", "Resource not found");

        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errors(mapErrors)
                .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleNoResourceFoundException(HttpRequestMethodNotSupportedException e) {

        Map<String, String> mapErrors = new HashMap<>();

        mapErrors.put("error", "method not supported");

        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .type(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                .errors(mapErrors)
                .build()
                , HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleNoResourceFoundException(SQLIntegrityConstraintViolationException e) {

        Map<String, String> mapErrors = new HashMap<>();

        mapErrors.put("unique_error", e.getMessage().split(" for key ")[0]);

        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .type(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .errors(mapErrors)
                .build()
                , HttpStatus.NOT_ACCEPTABLE);
    }
}