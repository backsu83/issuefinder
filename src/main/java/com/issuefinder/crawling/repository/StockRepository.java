package com.issuefinder.crawling.repository;

import com.issuefinder.crawling.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository  extends JpaRepository<Stock, Integer> {

    Stock findStockByCompanyCode(String compayCode);
    List<Stock> findAll();
//
//    private final DSLContext dsl;
//
//    @Autowired
//    public StockRepository(DSLContext dslContext) {
//        this.dsl = dslContext;
//    }
//
//    public StockEntity findByCompanyCode(String companyCode) {
//
//        return dsl.select()
//                .from(STOCK)
//                .where(STOCK.COMPANY_CODE.eq(companyCode))
//                .fetchOptionalInto(StockEntity.class)
//                .orElseThrow(()-> new ResourceNotFoundException(companyCode));
//    }
//
//    public List<String> findAll() {
//        return dsl.select(STOCK.COMPANY_CODE)
//                .from(STOCK)
//                .fetch()
//                .into(String.class);
//    }

}
