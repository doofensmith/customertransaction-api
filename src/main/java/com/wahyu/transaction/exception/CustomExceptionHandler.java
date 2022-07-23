package com.wahyu.transaction.exception;

import com.wahyu.transaction.constant.AppConstant;
import com.wahyu.transaction.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> response(NullPointerException ex) {
        return ResponseUtil.build(HttpStatus.BAD_REQUEST, AppConstant.BAD_REQUEST.message, null);
    }

}
