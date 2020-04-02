package com.issuefinder.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockArticle {

    private String companyCode;
    private LocalDate collectDay;
    private String refer;
    private Integer views;
    private Integer sympathy;
    private Integer unsympathy;
    private Integer score;
}

