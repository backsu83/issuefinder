package com.issuefinder.crawling.service;

import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.entity.Article;
import com.issuefinder.crawling.model.entity.Sise;
import com.issuefinder.crawling.model.entity.Stock;
import com.issuefinder.crawling.repository.ArticleRepository;
import com.issuefinder.crawling.repository.SiseRepository;
import com.issuefinder.crawling.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;
    private final SiseRepository siseRepository;
    private final ArticleRepository articleRepository;

    public Stock getStock(String companyCode) {
        Stock stocks =  repository.findStockByCompanyCode(companyCode);
        if (ObjectUtils.isEmpty(stocks)) {
            throw new ResourceNotFoundException(companyCode);
        }
        return stocks;
    }

    public List<Stock> getAll() {
        return repository.findAll();
    }

    public List<Sise> getSiseAll() {
        return siseRepository.findAll();
    }

    public List<Article> getArticleAll() {
        return articleRepository.findAll();
    }

}
