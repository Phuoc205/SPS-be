package com.smartparking.shared.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiError> handleAppException(AppException ex) {

        return ResponseEntity.badRequest().body(
                new ApiError(
                        ex.getCode().name(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex) {

        return ResponseEntity.internalServerError().body(
                new ApiError(
                        ErrorCode.INTERNAL_ERROR.name(),
                        ex.getMessage()
                )
        );
    }
}