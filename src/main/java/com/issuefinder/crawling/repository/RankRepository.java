package com.issuefinder.crawling.repository;

import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.entity.RankEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.issuefinder.crawling.generated.Tables.STOCK_RANK;


@Repository
public class RankRepository {

    private final DSLContext dsl;

    @Autowired
    public RankRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(CrawlerDto crawler) {
        for (Map.Entry<String , Integer> info : crawler.getCrawlerList().entrySet()) {
            int rowsAffected = dsl.insertInto(STOCK_RANK)
                    .set(STOCK_RANK.COMPANY_CODE , crawler.getCompanyCode())
                    .set(STOCK_RANK.COLLECT_DAY , LocalDate.parse(info.getKey() , DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .set(STOCK_RANK.SCORE , info.getValue())
                    .set(STOCK_RANK.REFER , crawler.getRefer())
                    .set(STOCK_RANK.RESOURCE_TYPE , crawler.getResourceType())
                    .execute();
            if (rowsAffected != 1) {
                throw new IncorrectResultSizeDataAccessException(1, rowsAffected);
            }
        }
    }

    public List<RankEntity> findRankListByCompanyCode(String companyCode) {

        return dsl.select()
                .from(STOCK_RANK)
                .where(STOCK_RANK.COMPANY_CODE.eq(companyCode))
                .fetch()
                .into(RankEntity.class);
    }

    public void deleteByCompanyCode(String companyCode) {
        int rowsAffected =  dsl.deleteFrom(STOCK_RANK)
                .where(STOCK_RANK.COMPANY_CODE.eq(companyCode))
                .execute();
        if(rowsAffected == 0) {
            throw new IncorrectResultSizeDataAccessException(rowsAffected);
        }
    }
}
