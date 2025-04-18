package com.archit.tasklist_api.controller;

import com.archit.tasklist_api.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( { IllegalArgumentException.class } )
    public ResponseEntity< ErrorResponse > handleExceptions( RuntimeException ex, WebRequest req ) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                req.getDescription( false )
        );

        return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST );
    }
}
