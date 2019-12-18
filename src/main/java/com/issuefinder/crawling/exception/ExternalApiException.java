package com.issuefinder.crawling.exception;

import com.issuefinder.crawling.contants.ErrorCode;
import lombok.Getter;

@Getter
public class ExternalApiException extends RuntimeException {

    private ErrorCode errorCode;
    private int statusCode;
    private String message;

    public ExternalApiException(ErrorCode errorCode, int statusCode, String message) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ExternalApiException(ErrorCode errorCode , String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
