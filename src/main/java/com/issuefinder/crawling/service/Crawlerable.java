package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.model.CrawlerDto;

public interface Crawlerable {
    CrawlerDto parser(CrawlerRequest request);
}
