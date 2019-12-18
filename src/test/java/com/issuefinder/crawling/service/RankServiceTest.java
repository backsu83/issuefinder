package com.issuefinder.crawling.service;

import com.issuefinder.crawling.CrawlingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = {CrawlingApplication.class})
class RankServiceTest {

    @Test
    void name() {
        //System.out.println(LocalDate.now().minusDays(7L).toString());
        LocalDate today = LocalDate.now();
        int count =0 ;
        System.out.println(LocalDate.now().minusDays(14L));
        if(LocalDate.parse("2019-11-17").isBefore(LocalDate.now().minusDays(14L))) {

            System.out.println(LocalDate.now().toString());
        }

    }

    @Test
    void name2() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

//Create stream of dates
        List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toList());

// Get Min or Max Date
        LocalDate maxDate = dates.stream()
                .max( Comparator.comparing( LocalDate::toEpochDay ) )
                .get();

        LocalDate minDate = dates.stream()
                .min( Comparator.comparing( LocalDate::toEpochDay ) )
                .get();

        System.out.println("maxDate = " + maxDate);
        System.out.println("minDate = " + minDate);
    }
}
