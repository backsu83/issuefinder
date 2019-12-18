package com.issuefinder.crawling.service;

import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.entity.StockEntity;
import com.issuefinder.crawling.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    public StockEntity getStock(String companyCode) {
        StockEntity stocks =  repository.findByCompanyCode(companyCode);
        if (ObjectUtils.isEmpty(stocks)) {
            throw new ResourceNotFoundException(companyCode);
        }
        return stocks;
    }

    public List<String> getAll() {
        return repository.findAll();
    }

}
