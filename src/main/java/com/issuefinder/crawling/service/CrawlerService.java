package com.issuefinder.crawling.service;

import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.entity.RankEntity;
import com.issuefinder.crawling.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CrawlerService {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private StockService stockService;

    public void saveAll() {
        List<String> companyCodes =  stockService.getAll();
        for (String companyCode : companyCodes) {
            CrawlerDto crawler = new NaverArticleCrawler().parser(companyCode);
            rankRepository.save(crawler);
        }
    }

    public void saveArticle(String companyCode) {
        CrawlerDto crawler = new NaverArticleCrawler().parser(companyCode);
        rankRepository.save(crawler);
    }

    public void savePrice(String companyCode) {
        CrawlerDto crawler = new NaverPriceCrawler().parser(companyCode);
        //rankRepository.save(crawler);
    }


    public List<RankEntity> getRankList(String companyCode) {
        List<RankEntity> list = rankRepository.findRankListByCompanyCode(companyCode);
        if(list.size() == 0) {
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
