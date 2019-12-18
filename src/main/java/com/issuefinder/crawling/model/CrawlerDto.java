package com.issuefinder.crawling.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CrawlerDto {

    private String companyCode;
    private String refer;
    private Integer resourceType;
    private Map<String, Integer> crawlerList;
}
