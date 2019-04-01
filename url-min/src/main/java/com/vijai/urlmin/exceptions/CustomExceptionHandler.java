package com.vijai.urlmin.exceptions;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({RedisConnectionFailureException.class})
    public ResponseEntity<String> handleRunTimeException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sometning went wrong on server");
    }
}
