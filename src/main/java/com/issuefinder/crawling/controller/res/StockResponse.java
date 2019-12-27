package com.issuefinder.crawling.controller.res;


import com.issuefinder.crawling.contants.MessageCode;
import lombok.Getter;

@Getter
public class StockResponse<T> {

    String code;
    T data;

    public StockResponse(T data , MessageCode code) {
        this.data = data;
        this.code = code.name();
    }
}
