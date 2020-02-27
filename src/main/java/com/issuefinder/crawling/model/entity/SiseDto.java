package com.issuefinder.crawling.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiseDto {
    private String companyCode;
    private Integer closingPrice;
    private Long volume;
    private Integer views;
    private Integer sympathy;
    private Integer unsympathy;
    private Integer score;
}
