package com.issuefinder.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NaverSise {

    private String collectDay;
    private int closingPrice;
    private int openingPrice;
    private int comparePrice;
    private int topPrice;
    private int lowPrice;
    private long volume;

    public NaverSise(String collectDay, int closingPrice , long volume) {
        this.collectDay = collectDay;
        this.closingPrice = closingPrice;
        this.volume = volume;
    }


}
