package com.issuefinder.crawling.config;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.context.annotation.Bean;

public class CrawlerConfig {

    @Bean
    public CrawlConfig getConfig() {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder("data/storage");
        config.setIncludeHttpsPages(true);
        config.setMaxDepthOfCrawling(1);
        config.setPolitenessDelay(500);
        return config;
    }

    @Bean
    public CrawlController getCrawlerController() throws Exception {
        PageFetcher pageFetcher = new PageFetcher(getConfig());
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig , pageFetcher);
        CrawlController crawlController = new CrawlController(getConfig() , pageFetcher , robotstxtServer);
        return crawlController;
    }
}
