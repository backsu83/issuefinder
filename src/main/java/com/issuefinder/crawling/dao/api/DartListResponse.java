package com.issuefinder.crawling.dao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DartListResponse {
    private String status;
    private String message;
    private String page_no;
    private String page_count;
    private String total_count;
    private List<DartInformation> list;
}
