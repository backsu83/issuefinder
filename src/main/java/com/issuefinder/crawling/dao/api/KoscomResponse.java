package com.issuefinder.crawling.dao.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KoscomResponse {

    private String jsonrpc;
    private KoscomRealTimePrice result;
//    private List<KoscomRealTimePrice> isulist;
}
