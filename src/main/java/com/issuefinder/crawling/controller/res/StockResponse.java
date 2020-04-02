package com.issuefinder.crawling.controller.res;


import com.issuefinder.crawling.contants.MessageCode;
import com.issuefinder.crawling.model.StockRealAnalysis;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StockResponse<T> {

    String code;
    T data;

    public StockResponse(T data , MessageCode code) {
        this.data = data;
        this.code = code.name();
    }

    public StockResponse(T data) {
        this.data = data;
        this.code = MessageCode.SUCCESS.name();
    }

}
