package com.issuefinder.crawling.repository;

import com.issuefinder.crawling.exception.ResourceNotFoundException;
import com.issuefinder.crawling.model.entity.StockEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.issuefinder.crawling.generated.Tables.STOCK;

@Repository
public class StockRepository {

    private final DSLContext dsl;

    @Autowired
    public StockRepository(DSLContext dslContext) {
        this.dsl = dslContext;
    }

    public StockEntity findByCompanyCode(String companyCode) {

        return dsl.select()
                .from(STOCK)
                .where(STOCK.COMPANY_CODE.eq(companyCode))
                .fetchOptionalInto(StockEntity.class)
                .orElseThrow(()-> new ResourceNotFoundException(companyCode));
    }

    public List<String> findAll() {
        return dsl.select(STOCK.COMPANY_CODE)
                .from(STOCK)
                .fetch()
                .into(String.class);
    }
}
