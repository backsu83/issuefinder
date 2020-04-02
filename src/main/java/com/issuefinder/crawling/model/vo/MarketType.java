package com.issuefinder.crawling.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarketType {

    KOSDAQ("kosdaq") ,
    KOSPI("kospi");

    private String name;
}
