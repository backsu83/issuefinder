package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.entity.Article;
import com.issuefinder.crawling.model.entity.Sise;
import com.issuefinder.crawling.model.entity.Stock;
import com.issuefinder.crawling.model.entity.StockBase;
import com.issuefinder.crawling.repository.ArticleRepository;
import com.issuefinder.crawling.repository.SiseRepository;
import com.issuefinder.crawling.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
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

    private final ArticleRepository articleRepository;
    private final SiseRepository siseRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final Crawlerable naverArticleCrawler;
    private final Crawlerable naverSiseCrawler;

    @Transactional
    public void saveAll(CrawlerRequest request) {
        List<Stock> companyCodes = stockService.getAll();
        for (Stock info : companyCodes) {
            request.setCompanyCode(info.getCompanyCode());
            saveArticle(request);
            saveSise(request);
        }
    }

    public void saveArticle(CrawlerRequest request) {
        Map<String, Summary> map = naverArticleCrawler.parser(request);
        for (Map.Entry<String, Summary> elem : map.entrySet()) {
            Article rank = new Article();
            rank.setCollectDay(LocalDate.parse(elem.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            rank.setRefer(NAVER.name());
            rank.setCompanyCode(request.getCompanyCode());
            rank.setScore(elem.getValue().getCount());
            rank.setSympathy(elem.getValue().getSympathy());
            rank.setUnsympathy(elem.getValue().getUnsympathy());
            rank.setViews(elem.getValue().getViews());
            articleRepository.save(rank);
        }
    }

    public void saveSise(CrawlerRequest request) {
        Map<String, Summary> map = naverSiseCrawler.parser(request);
        for (Map.Entry<String, Summary> elem : map.entrySet()) {
            Sise sise = new Sise();
            sise.setCollectDay(LocalDate.parse(elem.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            sise.setRefer(NAVER.name());
            sise.setCompanyCode(request.getCompanyCode());
            sise.setClosingPrice(elem.getValue().getClosingPrice());
            sise.setVolume(elem.getValue().getVolume());
            siseRepository.save(sise);
        }
    }

    public JSONArray getSiseAndAricle(String companyCode) {
        List<Sise> siseRepo = siseRepository.findRanksByCompanyCode(companyCode);
        List<Article> articleRepo = articleRepository.findRanksByCompanyCode(companyCode);
        Stock stock = stockRepository.findStockByCompanyCode(companyCode);
        JSONArray resArray  = new JSONArray();
        StockBase base = null;
        for (Article article : articleRepo) {
            base = new StockBase();
            base.setCompanyCode(article.getCompanyCode());
            base.setViews(article.getViews());
            base.setSympathy(article.getSympathy());
            base.setUnsympathy(article.getUnsympathy());
            base.setScore(article.getScore());
            base.setCompanyName(stock.getCompanyName());
            for (Sise sise : siseRepo) {
                if(sise.getCollectDay().equals(article.getCollectDay())) {
                    base.setVolume(sise.getVolume());
                    base.setClosingPrice(sise.getClosingPrice());
                }
            }
            base.setDate(article.getCollectDay().toString());
            resArray.add(base);
        }
        return resArray;
    }

    public void deleteBy(String companyCode) {
        int size = articleRepository.findRanksByCompanyCode(companyCode).size();
        if (size == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        articleRepository.deleteByCompanyCode(companyCode);
    }
}
