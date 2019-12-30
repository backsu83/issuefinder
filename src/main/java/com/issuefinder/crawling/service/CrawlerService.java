package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.entity.Rank;
import com.issuefinder.crawling.model.entity.Stock;
import com.issuefinder.crawling.model.vo.ResourceType;
import com.issuefinder.crawling.repository.RankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.issuefinder.crawling.model.vo.ReferType.NAVER;
import static com.issuefinder.crawling.model.vo.ResourceType.ARTICLE;
import static com.issuefinder.crawling.model.vo.ResourceType.SISE;


@Service
public class CrawlerService {

    private RankRepository rankRepository;
    private StockService stockService;
    private Crawlerable naverArticleCrawler;
    private Crawlerable naverSiseCrawler;

    public CrawlerService(final RankRepository rankRepository,
                          final StockService stockService,
                          final NaverArticleCrawler naverArticleCrawler,
                          final NaverSiseCrawler naverSiseCrawler
    ) {
        this.rankRepository = rankRepository;
        this.stockService = stockService;
        this.naverArticleCrawler = naverArticleCrawler;
        this.naverSiseCrawler = naverSiseCrawler;
    }

    @Transactional
    public void saveAll(CrawlerRequest request) {
        List<Stock> companyCodes = stockService.getAll();
        CrawlerDto crawlerDto = null;
        for (Stock info : companyCodes) {
            request.setCompanyCode(info.getCompanyCode());
            if (request.getResourceType() == ResourceType.ARTICLE) {
                crawlerDto = naverArticleCrawler.parser(request);
            } else if (request.getResourceType() == ResourceType.SISE) {
                crawlerDto = naverSiseCrawler.parser(request);
            }

            for (Map.Entry<String, Integer> craw : crawlerDto.getCrawlerList().entrySet()) {
                Rank rank = new Rank();
                rank.setCompanyCode(crawlerDto.getCompanyCode());
                rank.setRefer(NAVER.name());
                rank.setResourceType(ARTICLE.getCode());
                rank.setCollectDay(LocalDate.parse(craw.getKey() , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                rank.setScore(craw.getValue());
                rankRepository.save(rank);
            }
        }
    }

    public void saveArticle(CrawlerRequest request) {
        CrawlerDto crawler = naverArticleCrawler.parser(request);
        for (Map.Entry<String, Integer> info : crawler.getCrawlerList().entrySet()) {
            Rank rank = new Rank();
            rank.setCollectDay(LocalDate.parse(info.getKey() , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            rank.setRefer(NAVER.name());
            rank.setResourceType(ARTICLE.getCode());
            rank.setCompanyCode(crawler.getCompanyCode());
            rank.setScore(info.getValue());
            rankRepository.save(rank);
        }

    }

    public void saveSise(CrawlerRequest request) {
        CrawlerDto crawler = naverSiseCrawler.parser(request);
        for (Map.Entry<String, Integer> info : crawler.getCrawlerList().entrySet()) {
            Rank rank = new Rank();
            rank.setCollectDay(LocalDate.parse(info.getKey() , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            rank.setRefer(NAVER.name());
            rank.setResourceType(SISE.getCode());
            rank.setCompanyCode(crawler.getCompanyCode());
            rank.setScore(info.getValue());
            rankRepository.save(rank);
        }
    }

    public List<Rank> getRankList(String companyCode) {
        List<Rank> list = rankRepository.findRanksByCompanyCode(companyCode);
        if (list.size() == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        return list;
    }

    public void deleteBy(String companyCode) {
        int size = rankRepository.findRanksByCompanyCode(companyCode).size();
        if (size == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        rankRepository.deleteByCompanyCode(companyCode);
    }
}
