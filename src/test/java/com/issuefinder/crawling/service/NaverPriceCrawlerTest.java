package com.issuefinder.crawling.service;

import com.issuefinder.crawling.config.properties.HostProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class NaverPriceCrawlerTest {

    @Autowired
    HostProperties hostProperties;

    @Test
    void hostProperties() {
        System.out.println(hostProperties.getNaverPrice());
    }

    @Test
    void now() {
        System.out.println(LocalDateTime.now());
    }
}
