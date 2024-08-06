package com.emovies.movieMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<MovieException> movieExceptionHandler(MovieException e){
        MovieException exception = new MovieException(e.getMessage(), e.getStatusCode());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }
}
