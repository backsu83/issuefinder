package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.model.CrawlerDto;

import java.util.Map;

public interface Crawlerable {
    Map<String, Summary> parser(CrawlerRequest request);
}
