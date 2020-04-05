package com.issuefinder.crawling.service;

import com.issuefinder.crawling.dao.api.KoscomApi;
import com.issuefinder.crawling.dao.api.KoscomCompanyInfo;
import com.issuefinder.crawling.dao.api.KoscomRealTimePrice;
import com.issuefinder.crawling.dao.api.KoscomStockOhlc;
import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.mapper.StockCompanyMapper;
import com.issuefinder.crawling.mapper.StockPriceMapper;
import com.issuefinder.crawling.model.StockCompany;
import com.issuefinder.crawling.model.StockOhlc;
import com.issuefinder.crawling.model.StockPrice;
import com.issuefinder.crawling.model.StockRealAnalysis;
import com.issuefinder.crawling.model.vo.MarketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockCompanyMapper companyMapper;
    private final StockPriceMapper priceMapper;
    private final KoscomApi koscomApi;

    public StockCompany getStockCompany(String companyCode) {
        StockCompany stocks = companyMapper.findCompanyInfo(companyCode);
        if (ObjectUtils.isEmpty(stocks)) {
            throw new ResourceNotFoundException(companyCode);
        }
        return stocks;
    }

    public StockRealAnalysis getStockPrice(String companyCode, String startDate, String endDate) {
        List<StockPrice> stockprice = priceMapper.findPrice(companyCode, startDate, endDate);
        StockCompany stocks = companyMapper.findCompanyInfo(companyCode);

        int score = priceMapper.findScore(companyCode, startDate, endDate);
        StockRealAnalysis real = new StockRealAnalysis();
        int tradePrcie = getRealTimePrice(companyCode);

        real.setCompanyName(stocks.getCompanyName());
        real.setCompanyCode(stocks.getCompanyCode());
        real.setTradePrice(tradePrcie);
        real.setScore(score);
        real.setPriceList(stockprice);

        return real;
    }
    
    public List<StockOhlc> getOhlclists(String companyCode) {
        List<StockOhlc> ohlc = priceMapper.findOhlc(companyCode);
        return ohlc;
    }

    public int saveOhlc() {
        KoscomStockOhlc kosdaq = koscomApi.getOhlclists(MarketType.KOSDAQ.getName());
        for(KoscomStockOhlc.StockOhlc ohlc : kosdaq.getIsuLists()) {
            ohlc.setCollectDay(LocalDate.now().toString());
            priceMapper.saveOhlc(ohlc);
        }

        KoscomStockOhlc kospi = koscomApi.getOhlclists(MarketType.KOSPI.getName());
        for(KoscomStockOhlc.StockOhlc ohlc : kospi.getIsuLists()) {
            ohlc.setCollectDay(LocalDate.now().toString());
            priceMapper.saveOhlc(ohlc);
        }
        return 1;
    }

    public int getRealTimePrice(String companyCode) {
        StockCompany company = companyMapper.findCompanyInfo(companyCode);
        StockCompany companyOther = companyMapper.findCompanyOther(company.getMarketType());
        String companys = company.getCompanyCode() + "," + companyOther.getCompanyCode();

        KoscomRealTimePrice realTimePrice = koscomApi.getRealTimePrice(companys, company.getMarketType().toLowerCase());
        for (KoscomRealTimePrice.RealPrice realPrice : realTimePrice.getIsulist()) {
            if(realPrice.getIsuSrtCd().equals(company.getCompanyCode())) {
                return Integer.valueOf(realPrice.getTrdPrc());
            }
        }
        return 0;
    }

    public List<StockCompany> getAll() {
        return companyMapper.findCompanyList();
    }

    public void updateStock(String market) {
        List<KoscomCompanyInfo> kosdaq = koscomApi.getStockList(market);
        for(KoscomCompanyInfo companyInfo : kosdaq ) {
            companyInfo.setMarket(market.toUpperCase());
            companyMapper.save(companyInfo);
        }
    }
}
