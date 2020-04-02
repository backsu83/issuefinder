package com.issuefinder.crawling.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockRealAnalysis {

    private String companyName;
    private String companyCode;
    private Integer tradePrice;
    private Integer score;
    private List<StockPrice> priceList;

}
