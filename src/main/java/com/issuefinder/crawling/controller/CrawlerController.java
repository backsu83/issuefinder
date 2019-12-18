package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

//    @GetMapping("/crawler/paxnet")
//    public StockResponse getRank()  {
//        return new StockResponse<>(rankService.paxnetRank(), SUCCESS);
//    }

    @GetMapping("/crawler/article/all")
    public ResponseEntity saveAricleAll()  {
        crawlerService.saveAll();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/crawler/article/{companyCode}")
    public ResponseEntity saveArticle(@PathVariable String companyCode)  {
        crawlerService.saveArticle(companyCode);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/crawler/{companyCode}")
    public ResponseEntity delelteBy(@PathVariable String companyCode) {
        crawlerService.deleteBy(companyCode);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/crawler/price/{companyCode}")
    public ResponseEntity savePrice(@PathVariable String companyCode)  {
        crawlerService.savePrice(companyCode);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
