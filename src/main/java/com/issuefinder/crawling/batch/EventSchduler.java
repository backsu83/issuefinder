package com.issuefinder.crawling.batch;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.model.vo.ResourceType;
import com.issuefinder.crawling.service.CrawlerService;
import com.issuefinder.crawling.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventSchduler {

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0 23 * * *")
    public void batchSise() {
        crawlerService.saveAll(CrawlerRequest.builder()
                .resourceType(ResourceType.SISE)
                .limitDays(1L)
                .build());
    }

    @Scheduled(cron = "0 0 22 * * *")
    public void batchOhlc() {
        stockService.saveOhlc();
    }
}
