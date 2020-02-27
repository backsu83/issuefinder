package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.api.DartApi;
import com.issuefinder.crawling.controller.res.StockResponse;
import com.issuefinder.crawling.service.CrawlerService;
import com.issuefinder.crawling.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.issuefinder.crawling.contants.MessageCode.SUCCESS;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final CrawlerService crawlerService;
    private final DartApi dartApi;

//    @GetMapping("/stock/{companyCode}")
//    public StockResponse getStock(@PathVariable String companyCode) {
//        return new StockResponse(stockService.getStock(companyCode), SUCCESS);
//    }

    @GetMapping("/stock/{companyCode}/article")
    public StockResponse getRank(@PathVariable String companyCode) {
        return new StockResponse(crawlerService.getAricle(companyCode), SUCCESS);
    }

    @GetMapping("/stock/{companyCode}/sise")
    public StockResponse getSise(@PathVariable String companyCode) {
        return new StockResponse(crawlerService.getSise(companyCode), SUCCESS);
    }

    @GetMapping("/stock/{companyCode}")
    public StockResponse getSiseAndAricle(@PathVariable String companyCode) {
        return new StockResponse(crawlerService.getSiseAndAricle(companyCode), SUCCESS);
    }

    @GetMapping("stock/article/all")
    public StockResponse getArticleAll() {
        return new StockResponse(stockService.getArticleAll() , SUCCESS);
    }


    @GetMapping("stock/dart")
    public StockResponse getInformation() {
        return new StockResponse(dartApi.getList() , SUCCESS);
    }
}
