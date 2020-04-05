package com.issuefinder.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockCompany {

    private String companyCode;
    private String companyName;
    private String marketType;
}
