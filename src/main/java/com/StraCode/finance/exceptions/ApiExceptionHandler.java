package com.StraCode.finance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException e){
       return ResponseEntity.status(HttpStatus.NOT_FOUND
       ).body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now()));
    }

    @ExceptionHandler(value = NullDtoException.class)
    public ResponseEntity<ErrorResponse> HandleNullDtoException(NullDtoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> HandleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("The input is invalid", HttpStatus.BAD_REQUEST, ZonedDateTime.now()));
    }

    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> HandleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("The input is invalid: ", HttpStatus.BAD_REQUEST, ZonedDateTime.now()));

    }

    @ExceptionHandler(value =  NegativeAmountException.class)
    public ResponseEntity<ErrorResponse> HandleNegativeAmountException(NegativeAmountException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("The amount cant be a negative value!", HttpStatus.BAD_REQUEST, ZonedDateTime.now()));

    }
}
