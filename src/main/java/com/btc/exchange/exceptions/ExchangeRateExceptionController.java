package com.btc.exchange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExchangeRateExceptionController {

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<Object> exception(InternalServerException exception) {
        return new ResponseEntity<>("OOPS, something went wrong!", HttpStatus.NOT_FOUND);
    }
}
