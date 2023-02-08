package com.practice.currency_exchange.Exception;

import com.practice.currency_exchange.Exception.NotFoundException;
import com.practice.currency_exchange.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<MessageResponse> notFoundException(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse(ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<MessageResponse> existingException(ExistingException ex){
        return ResponseEntity.status(HttpStatus.IM_USED)
                .body(new MessageResponse(ex.getMessage()));
    }


}
