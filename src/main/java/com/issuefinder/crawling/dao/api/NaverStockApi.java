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

    public void test() {
        String url = "/properties/contents/";
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_HOST + url);
        URI uri = builder.queryParams(paramMap).build().encode().toUri();

       // ResponseEntity<> response = restTemplate.postForEntity(uri, new HttpEntity<>(headers), PushResponse.class);

        //ParameterizedTypeReference responseType = new ParameterizedTypeReference<List<ContentPropertiesReceiveDto>>() {};

//        ResponseEntity<List<ContentPropertiesReceiveDto>> responseEntityData
//                = ApiExecutor.execute(() -> restTemplate.exchange(uri, HttpMethod.GET, null, responseType));
//        return ApiExecutor.getBody(responseEntityData, this.API_HOST);
//

    }
}
