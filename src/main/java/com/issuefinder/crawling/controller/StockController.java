package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.res.StockResponse;
import com.issuefinder.crawling.service.CrawlerService;
import com.issuefinder.crawling.service.StockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.issuefinder.crawling.contants.MessageCode.SUCCESS;

@Validated
@RestController
@RequestMapping("/api")
public class StockController {

    private StockService stockService;
    private CrawlerService crawlerService;

    public StockController(final StockService stockService, final CrawlerService crawlerService) {
        this.stockService = stockService;
        this.crawlerService = crawlerService;
    }

    @GetMapping("/stock/{companyCode}")
    public StockResponse getStock(@PathVariable String companyCode) {
        return new StockResponse(stockService.getStock(companyCode), SUCCESS);
    }

    @GetMapping("/stock/{companyCode}/rank")
    public StockResponse getRank(@PathVariable String companyCode) {
        return new StockResponse(crawlerService.getRankList(companyCode), SUCCESS);
    }

    @GetMapping("stock/all")
    public StockResponse getAll() {
        return new StockResponse(stockService.getAll() , SUCCESS);
    }

}
