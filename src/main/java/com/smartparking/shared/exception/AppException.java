package com.smartparking.shared.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode code;

    public AppException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}