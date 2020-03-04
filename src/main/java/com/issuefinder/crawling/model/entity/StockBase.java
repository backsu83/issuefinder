package com.issuefinder.crawling.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockBase {
    private String companyCode;
    private String companyName;
    private Integer closingPrice;
    private Long volume;
    private Integer views;
    private Integer sympathy;
    private Integer unsympathy;
    private Integer score;
    private String date;
}
