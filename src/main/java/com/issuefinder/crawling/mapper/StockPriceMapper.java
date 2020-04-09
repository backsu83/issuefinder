package com.issuefinder.crawling.mapper;

import com.issuefinder.crawling.dao.api.KoscomStockBasic;
import com.issuefinder.crawling.dao.api.KoscomStockOhlc;
import com.issuefinder.crawling.model.StockArticle;
import com.issuefinder.crawling.model.StockOhlc;
import com.issuefinder.crawling.model.StockPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPriceMapper {
    List<StockPrice> findPrice(String companyCode, String startDate, String endDate);
    int findScore(String companyCode, String startDate, String endDate);
    List<StockOhlc> findOhlc(String companyCode);
    void saveOhlc(KoscomStockOhlc.StockOhlc ohlc);
    void saveStockList(KoscomStockBasic stock);
    void saveArticle(StockArticle article);
    void savePrice(StockPrice price);

}
