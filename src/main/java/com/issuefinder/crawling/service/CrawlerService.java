package com.issuefinder.crawling.service;

import com.google.common.collect.HashBiMap;
import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.entity.Article;
import com.issuefinder.crawling.model.entity.Sise;
import com.issuefinder.crawling.model.entity.SiseDto;
import com.issuefinder.crawling.model.entity.Stock;
import com.issuefinder.crawling.model.vo.ResourceType;
import com.issuefinder.crawling.repository.ArticleRepository;
import com.issuefinder.crawling.repository.SiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.issuefinder.crawling.model.vo.ReferType.NAVER;


@Service
public class CrawlerService {

    private ArticleRepository articleRepository;
    private SiseRepository siseRepository;
    private StockService stockService;
    private Crawlerable naverArticleCrawler;
    private Crawlerable naverSiseCrawler;

    public CrawlerService(final ArticleRepository articleRepository,
                          final StockService stockService,
                          final NaverArticleCrawler naverArticleCrawler,
                          final NaverSiseCrawler naverSiseCrawler,
                          final SiseRepository siseRepository
    ) {
        this.articleRepository = articleRepository;
        this.stockService = stockService;
        this.naverArticleCrawler = naverArticleCrawler;
        this.naverSiseCrawler = naverSiseCrawler;
        this.siseRepository = siseRepository;
    }

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

    public List<Article> getAricle(String companyCode) {
        List<Article> list = articleRepository.findRanksByCompanyCode(companyCode);
        if (list.size() == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        return list;
    }

    public List<Sise> getSise(String companyCode) {
        List<Sise> list = siseRepository.findRanksByCompanyCode(companyCode);
        if (list.size() == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        return list;
    }

    public HashBiMap getSiseAndAricle(String companyCode) {
        List<Sise> siseRepo = siseRepository.findRanksByCompanyCode(companyCode);
        List<Article> articleRepo = articleRepository.findRanksByCompanyCode(companyCode);
        HashBiMap bimap = HashBiMap.create();

        for (Article article : articleRepo) {
            for (Sise sise : siseRepo) {
                SiseDto sisedto = new SiseDto();
                if (sise.getCompanyCode().equals(article.getCompanyCode())) {
                    sisedto.setCompanyCode(sise.getCompanyCode());
                    sisedto.setVolume(sise.getVolume());
                    sisedto.setClosingPrice(sise.getClosingPrice());
                    sisedto.setViews(article.getViews());
                    sisedto.setSympathy(article.getSympathy());
                    sisedto.setUnsympathy(article.getUnsympathy());
                    sisedto.setScore(article.getScore());
                    bimap.put(article.getCollectDay(), sisedto);
                }
            }
        }
        return bimap;
    }

    public void deleteBy(String companyCode) {
        int size = articleRepository.findRanksByCompanyCode(companyCode).size();
        if (size == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        articleRepository.deleteByCompanyCode(companyCode);
    }
}
