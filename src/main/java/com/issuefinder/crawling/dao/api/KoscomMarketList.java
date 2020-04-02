package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoscomMarketList {

    private String trdDd;
    private String mktEndTm;
    private List<KoscomCompanyInfo> isuLists;
}
