package com.wahyu.transaction.util;

import com.wahyu.transaction.domain.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ResponseUtil {

    private ResponseUtil() {
        //
    }

    public static ResponseEntity<Object> build(HttpStatus httpStatus, String message, Object data) {
        ApiResponse response = ApiResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("GMT+7")))
                .responseCode(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }

}
