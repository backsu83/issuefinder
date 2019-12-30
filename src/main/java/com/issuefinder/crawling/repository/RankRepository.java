package com.issuefinder.crawling.repository;

import com.issuefinder.crawling.model.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RankRepository extends JpaRepository<Rank, Integer> {

    List<Rank> findRanksByCompanyCode(String companyCode);
    void deleteByCompanyCode(String companyCode);
//    private final DSLContext dsl;
//
//    @Autowired
//    public RankRepository(DSLContext dsl) {
//        this.dsl = dsl;
//    }
//
//    public void save(CrawlerDto crawler) {
//        for (Map.Entry<String , Integer> info : crawler.getCrawlerList().entrySet()) {
//            int rowsAffected = dsl.insertInto(STOCK_RANK)
//                    .set(STOCK_RANK.COMPANY_CODE , crawler.getCompanyCode())
//                    .set(STOCK_RANK.COLLECT_DAY , LocalDate.parse(info.getKey() , DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                    .set(STOCK_RANK.SCORE , info.getValue())
//                    .set(STOCK_RANK.REFER , crawler.getRefer())
//                    .set(STOCK_RANK.RESOURCE_TYPE , crawler.getResourceType())
//                    .execute();
//            if (rowsAffected != 1) {
//                throw new IncorrectResultSizeDataAccessException(1, rowsAffected);
//            }
//        }
//    }
//
//    public List<RankEntity> findRankListByCompanyCode(String companyCode) {
//
//        return dsl.select()
//                .from(STOCK_RANK)
//                .where(STOCK_RANK.COMPANY_CODE.eq(companyCode))
//                .fetch()
//                .into(RankEntity.class);
//    }
//
//    public void deleteByCompanyCode(String companyCode) {
//        int rowsAffected =  dsl.deleteFrom(STOCK_RANK)
//                .where(STOCK_RANK.COMPANY_CODE.eq(companyCode))
//                .execute();
//        if(rowsAffected == 0) {
//            throw new IncorrectResultSizeDataAccessException(rowsAffected);
//        }
//    }
}
