package com.issuefinder.crawling.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    final String code;
    final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
