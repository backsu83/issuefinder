package com.issuefinder.crawling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockOhlc {

    private String companyCode;
    private String collectDay;
    private Integer openPrice;
    private Integer highPrice;
    private Integer lowPrice;
    private Integer tradePrice;

}
