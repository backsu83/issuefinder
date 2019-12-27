package com.issuefinder.crawling.controller.req;

import com.issuefinder.crawling.model.vo.ResourceType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class CrawlerRequest {
    private String companyCode;
    private ResourceType resourceType;
    private Long limitDays;
}
