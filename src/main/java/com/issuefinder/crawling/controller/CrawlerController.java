package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

//    @GetMapping("/crawler/paxnet")
//    public StockResponse getRank()  {
//        return new StockResponse<>(rankService.paxnetRank(), SUCCESS);
//    }

    @PostMapping("/crawler/all")
    public ResponseEntity saveAricleAll(CrawlerRequest request)  {
        crawlerService.saveAll(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/crawler/article")
    public ResponseEntity saveArticle(CrawlerRequest request)  {
        crawlerService.saveArticle(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/crawler/{companyCode}")
    public ResponseEntity delelteBy(@PathVariable String companyCode) {
        crawlerService.deleteBy(companyCode);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/crawler/sise")
    public ResponseEntity saveSise(CrawlerRequest request)  {
        crawlerService.saveSise(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/posttest")
    public void posttest() {
        System.out.println("POST");
    }

    @GetMapping("/gettest")
    public void gettest() {
        System.out.println("GET");
    }
}
