package com.issuefinder.crawling.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.List;

@Service
public class DartApi {

    private final RestTemplate restTemplate;

    public DartApi(final RestTemplateBuilder builder) {
        this.restTemplate = builder.setConnectTimeout(
                Duration.ofMillis(1000)).setReadTimeout(Duration.ofMillis(3000)).build();
    }

    public List<DartInformation> getList() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("crtfc_key", "08e4bc8989ff28ba72412d7aefef6fe40e49eada");

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("opendart.fss.or.kr")
                .path("/api/list.json")
                .queryParams(parameters).build().encode().toUri();

        ResponseEntity<DartListResponse> response
                = restTemplate.getForEntity(uri, DartListResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getList();
        }
        return null;
    }
}
