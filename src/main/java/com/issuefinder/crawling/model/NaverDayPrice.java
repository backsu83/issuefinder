package com.issuefinder.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NaverDayPrice {

    private String collectDay;
    private int closingPrice;
    private int volume;


}
