package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.res.StockResponse;
import com.issuefinder.crawling.dao.api.KoscomApi;
import com.issuefinder.crawling.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final KoscomApi koscomApi;

    @ApiOperation(value="종목 조회")
    @GetMapping("/stock/{companyCode}/info")
    public StockResponse getCompanyInfo(@PathVariable String companyCode) {
        return new StockResponse(stockService.getStockCompany(companyCode), SUCCESS);
    }

    @ApiOperation(value="종목 스코어 조회")
    @GetMapping("stock/{companyCode}/price")
    public StockResponse getStockPrice(
            @PathVariable String companyCode,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return new StockResponse(stockService.getStockPrice(companyCode, startDate, endDate));
    }

    @ApiOperation(value="종목 시고저중 조회")
    @GetMapping("stock/{market}/highlow")
    public StockResponse getStockPrice(@PathVariable String market) {
        return new StockResponse(koscomApi.getOhlclists(market), SUCCESS);
    }

    @ApiOperation(value="종목 현재가 조회")
    @GetMapping("stock/{companyCode}/realtime")
    public StockResponse getRealTimePrice(@PathVariable String companyCode) {
        return new StockResponse(stockService.getRealTimePrice(companyCode), SUCCESS);
    }

    @ApiOperation(value="종목전체 시고저중 저장")
    @GetMapping("stock/highlow/save")
    public ResponseEntity saveOhlc() {
        return new ResponseEntity<>(stockService.saveOhlc(), HttpStatus.OK);

    }
}
