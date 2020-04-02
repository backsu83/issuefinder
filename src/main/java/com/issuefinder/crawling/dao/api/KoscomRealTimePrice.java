package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoscomRealTimePrice {

    private List<RealPrice> isulist;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RealPrice  {
        private String trdPrc;
        private String cmpprevddTpCd;
        private String opnprc;
        private String hgprc;
        private String accTrdvol;
        private String trdTm;
        private String trdvol;
        private String accTrdval;
        private String cmpprevddPrc;
        private String isuSrtCd;
        private String bidordPrc_1;
        private String askordPrc_1;
        private String lstAskbidTpCd;

    }
}
