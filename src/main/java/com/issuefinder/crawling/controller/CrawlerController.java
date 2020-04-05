package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.service.CrawlerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @ApiOperation(value="크롤링 배치")
    @PostMapping("/crawler/all")
    public ResponseEntity saveAricleAll(CrawlerRequest request)  {
        crawlerService.saveAll(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value="네이버 게시글 크롤링")
    @PostMapping("/crawler/article")
    public ResponseEntity saveArticle(CrawlerRequest request)  {
        crawlerService.saveArticle(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value="네이버 현재가/종가 크롤링")
    @PostMapping("/crawler/price")
    public ResponseEntity savePrice(CrawlerRequest request)  {
        crawlerService.savePrice(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
