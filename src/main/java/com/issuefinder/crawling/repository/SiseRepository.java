package com.issuefinder.crawling.repository;

import com.issuefinder.crawling.model.entity.Sise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SiseRepository extends JpaRepository<Sise, Integer> {
    List<Sise> findRanksByCompanyCode(String companyCode);
    List<Sise> findRanksByCompanyCodeAndCollectDayBetween(String companyCode, LocalDate startDate , LocalDate endDate);
}
