package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.api.DartApi;
import com.issuefinder.crawling.controller.res.StockResponse;
import com.issuefinder.crawling.service.CrawlerService;
import com.issuefinder.crawling.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.issuefinder.crawling.contants.MessageCode.SUCCESS;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final CrawlerService crawlerService;
    private final DartApi dartApi;

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
