package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoscomStockOhlc {

    private String trdDd;
    private String trdTm;
    private List<StockOhlc> isuLists;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StockOhlc  {
        private String isuSrtCd;
        private String collectDay;
        private Integer opnprc;
        private Integer hgprc;
        private Integer lwprc;
        private Integer trdPrc;
    }
}
