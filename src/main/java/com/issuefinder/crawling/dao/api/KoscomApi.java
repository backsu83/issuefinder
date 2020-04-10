package com.issuefinder.crawling.dao.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.List;

@Service
public class KoscomApi {

    private final RestTemplate restTemplate;
    private final String AUTH_KEY = "l7xx02949c81f6c44cf19b3b648735a76720";

    public KoscomApi(final RestTemplateBuilder builder) {
        this.restTemplate = builder.setConnectTimeout(
                Duration.ofMillis(1000)).setReadTimeout(Duration.ofMillis(3000)).build();
    }

    public List<KoscomCompanyInfo> getStockList(String market) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("apikey", AUTH_KEY);

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("sandbox-apigw.koscom.co.kr")
                .path(String.format("/v2/market/stocks/%s/lists", market))
                .queryParams(parameters).build().encode().toUri();

        ResponseEntity<KoscomMarketList> response
                = restTemplate.getForEntity(uri, KoscomMarketList.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getIsuLists();
        }
        return null;
    }

    public KoscomStockOhlc getOhlclists(String market) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("apikey", AUTH_KEY);

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("sandbox-apigw.koscom.co.kr")
                .path(String.format("/v2/market/multiquote/stocks/%s/ohlclists" , market))
                .queryParams(parameters).build().encode().toUri();

        ResponseEntity<KoscomStockOhlc> response = restTemplate.getForEntity(uri, KoscomStockOhlc.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    
    // public KoscomStockOhlc getStockMaster(String market, String itemCode) {
    //     MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
    //     parameters.add("apikey", AUTH_KEY);

    //     URI uri = UriComponentsBuilder.newInstance()
    //             .scheme("https")
    //             .host("sandbox-apigw.koscom.co.kr")
    //             .path(String.format("/v2/market/multiquote/stocks/%s/ohlclists" , market))
    //             .queryParams(parameters).build().encode().toUri();

    //     ResponseEntity<KoscomStockOhlc> response = restTemplate.getForEntity(uri, KoscomStockOhlc.class);
    //     if (response.getStatusCode() == HttpStatus.OK) {
    //         return response.getBody();
    //     }
    //     return null;
    // }

    public KoscomStockBasic getNewStockList(String market) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("apikey", AUTH_KEY);

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("sandbox-apigw.koscom.co.kr")
                .path(String.format("/v2/market/multiquote/stocks/%s/lists", market))
                .queryParams(parameters).build().encode().toUri();

        ResponseEntity<KoscomStockBasic> response = restTemplate.getForEntity(uri, KoscomStockBasic.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            
            System.out.print(" res getBody >>>>> " + response.getBody());
            return response.getBody();
        }
        return null;
    }


    public KoscomRealTimePrice getRealTimePrice(String companys , String market) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("apikey", AUTH_KEY);
        parameters.add("isuCd", companys);

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("sandbox-apigw.koscom.co.kr")
                .path(String.format("/v2/market/multiquote/stocks/%s/price" , market))
                .queryParams(parameters).build().encode().toUri();

        ResponseEntity<KoscomResponse> response = restTemplate.getForEntity(uri, KoscomResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResult();
        }
        return null;
    }

//    public void getStockMaster(String market) {
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("apikey", AUTH_KEY);
//
//        URI uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("sandbox-apigw.koscom.co.kr")
//                .path(String.format("/v2/market/stocks/%s/master" , market))
//                .queryParams(parameters).build().encode().toUri();
//
//        ResponseEntity<KoscomStockMaster> response = restTemplate.getForEntity(uri, KoscomStockMaster.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return response.getBody();
//        }
//        return null;
//    }
}
