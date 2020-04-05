package com.issuefinder.crawling.mapper;

import com.issuefinder.crawling.dao.api.KoscomStockOhlc;
import com.issuefinder.crawling.model.StockArticle;
import com.issuefinder.crawling.model.StockPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPriceMapper {
    List<StockPrice> findPrice(String companyCode, String startDate, String endDate);
    int findScore(String companyCode, String startDate, String endDate);
    void saveOhlc(KoscomStockOhlc.StockOhlc ohlc);
    void saveArticle(StockArticle article);
    void savePrice(StockPrice price);

}