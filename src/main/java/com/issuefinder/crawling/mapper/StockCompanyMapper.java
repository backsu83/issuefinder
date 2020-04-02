package com.issuefinder.crawling.mapper;

import com.issuefinder.crawling.model.StockCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockCompanyMapper {
    StockCompany findCompanyInfo(String companyCode);
    StockCompany findCompanyOther(String market);
    List<StockCompany> findCompanyList();
}
