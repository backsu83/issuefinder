package com.issuefinder.crawling.dao.api.base;

import com.issuefinder.crawling.contants.ErrorCode;
import com.issuefinder.crawling.exception.ExternalApiException;

public abstract class ApiExecutor {

    private ApiExecutor() { }

    public static <R> R execute(ApiExecutable<R> executable) {
        try {
            return executable.execute();
        } catch (Exception ex) {
            throw new ExternalApiException(ErrorCode.EXTERNAL_API_ERROR , ex.getMessage());
        }
    }
}
