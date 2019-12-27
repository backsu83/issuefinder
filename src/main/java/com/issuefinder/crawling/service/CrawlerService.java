package com.issuefinder.crawling.service;

import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.entity.RankEntity;
import com.issuefinder.crawling.model.vo.ResourceType;
import com.issuefinder.crawling.repository.RankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        List<String> companyCodes = stockService.getAll();
        CrawlerDto crawlerDto = null;
        for (String companyCode : companyCodes) {
            request.setCompanyCode(companyCode);
            if(request.getResourceType() == ResourceType.ARTICLE) {
                crawlerDto = naverArticleCrawler.parser(request);
            } else if (request.getResourceType() == ResourceType.SISE) {
                crawlerDto = naverSiseCrawler.parser(request);
            }
            rankRepository.save(crawlerDto);
        }
    }

    public void saveArticle(CrawlerRequest request) {
        CrawlerDto crawler = naverArticleCrawler.parser(request);
        rankRepository.save(crawler);
    }

    public void saveSise(CrawlerRequest request) {
        CrawlerDto crawler = naverSiseCrawler.parser(request);
        rankRepository.save(crawler);
    }

    public List<RankEntity> getRankList(String companyCode) {
        List<RankEntity> list = rankRepository.findRankListByCompanyCode(companyCode);
        if (list.size() == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        return list;
    }

    public void deleteBy(String companyCode) {
        int size = rankRepository.findRankListByCompanyCode(companyCode).size();
        if (size == 0) {
            throw new ResourceNotFoundException(companyCode);
        }
        rankRepository.deleteByCompanyCode(companyCode);
    }
}
