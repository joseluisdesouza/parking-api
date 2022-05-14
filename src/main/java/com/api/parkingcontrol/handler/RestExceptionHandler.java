package com.api.parkingcontrol.handler;

import com.api.parkingcontrol.exception.ConflictException;
import com.api.parkingcontrol.exception.ConflictExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handlerConflictException(ConflictException conflictException) {
        return new ResponseEntity<>(
                ConflictExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .title("Conflict")
                        .details(conflictException.getMessage())
                        .developerMessage(conflictException.getClass().getName())
                        .build(), HttpStatus.CONFLICT);
    }
}