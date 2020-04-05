package com.issuefinder.crawling.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockPrice {
    private String companyCode;
    private LocalDate collectDay;
    private Integer closingPrice;
    private Long volume;
    private Integer views;
    private Integer score;
    private Integer sympathy;
    private Integer unsympathy;
}
