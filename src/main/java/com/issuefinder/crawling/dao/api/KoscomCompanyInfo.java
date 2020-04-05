package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoscomCompanyInfo {

    private String isuSrtCd;
    private String isuCd;
    private String isuKorNm;
    private String isuKorAbbr;
    private String market;
}
