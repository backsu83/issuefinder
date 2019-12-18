package com.issuefinder.crawling.exception;

import java.io.IOException;

public class JsoupConnectionException extends IOException {
    public JsoupConnectionException(String message) {
        super(message);
    }
}
