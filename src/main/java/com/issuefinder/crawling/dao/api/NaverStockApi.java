package com.issuefinder.crawling.dao.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class NaverStockApi {

    private String API_HOST;
    private RestTemplate restTemplate;

    @Autowired
    public NaverStockApi(@Value("${apis.contentv1.host}") String apiHost, RestTemplate restTemplate ) {
        this.API_HOST = apiHost;
        this.restTemplate = restTemplate;
    }
}
