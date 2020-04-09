package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoscomStockBasic {

    private String code;
    private String name;
    private String market;
    private String date;

}
