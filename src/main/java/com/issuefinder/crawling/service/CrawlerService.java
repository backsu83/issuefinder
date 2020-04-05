package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.dao.api.KoscomApi;
import com.issuefinder.crawling.dao.api.KoscomCompanyInfo;
import com.issuefinder.crawling.mapper.StockPriceMapper;
import com.issuefinder.crawling.model.StockArticle;
import com.issuefinder.crawling.model.StockCompany;
import com.issuefinder.crawling.model.StockPrice;
import com.issuefinder.crawling.model.vo.MarketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.issuefinder.crawling.model.vo.ReferType.NAVER;


@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final StockPriceMapper priceMapper;
    private final StockService stockService;
    private final Crawlerable naverArticleCrawler;
    private final Crawlerable naverSiseCrawler;

    public void saveAll(CrawlerRequest request) {
        List<StockCompany> companyCodes = stockService.getAll();
        for (StockCompany info : companyCodes) {
            request.setCompanyCode(info.getCompanyCode());
            saveArticle(request);
            savePrice(request);
        }
    }

    public void saveArticle(CrawlerRequest request) {
        Map<String, Summary> map = naverArticleCrawler.parser(request);
        for (Map.Entry<String, Summary> elem : map.entrySet()) {
            StockArticle article = new StockArticle();
            article.setCollectDay(LocalDate.parse(elem.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            article.setRefer(NAVER.name());
            article.setCompanyCode(request.getCompanyCode());
            article.setScore(elem.getValue().getCount());
            article.setSympathy(elem.getValue().getSympathy());
            article.setUnsympathy(elem.getValue().getUnsympathy());
            article.setViews(elem.getValue().getViews());
            priceMapper.saveArticle(article);
        }
    }

    public void savePrice(CrawlerRequest request) {
        Map<String, Summary> map = naverSiseCrawler.parser(request);
        for (Map.Entry<String, Summary> elem : map.entrySet()) {
            StockPrice sise = new StockPrice();
            sise.setCollectDay(LocalDate.parse(elem.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            sise.setCompanyCode(request.getCompanyCode());
            sise.setClosingPrice(elem.getValue().getClosingPrice());
            sise.setVolume(elem.getValue().getVolume());
            priceMapper.savePrice(sise);
        }
    }
}
