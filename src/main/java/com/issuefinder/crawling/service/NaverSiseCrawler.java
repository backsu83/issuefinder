package com.issuefinder.crawling.service;

import com.issuefinder.crawling.config.properties.HostProperties;
import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.NaverSise;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Component
class NaverSiseCrawler extends JsoupService implements Crawlerable {

    @Autowired
    private HostProperties host;

    @Override
    public Map<String, Summary> parser(CrawlerRequest request) {

        List<NaverSise> dayprice = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        LocalDate now = LocalDate.now();

        for (LocalDate start = now; start.isAfter(now.minusDays(request.getLimitDays())); start = start.minusDays(1)) {
            dayprice.add(new NaverSise(start.toString(), 0 , 0));
        }

        parserLoop: for (int i = 0; i < 20; i++) {
            Document doc = getJsuup(host.getNaverPrice() + "?code=" + request.getCompanyCode() + "&page=" + i);
            Elements elements = doc.select("tbody tr td .tah");
            int count = 1;

            for (Element element : elements) {
                queue.add(element.text());
                if (count % 7 == 0) {
                    try {
                        String collectDay = queue.pop().substring(0, 10).replace(".", "-");
                        dayprice.add(new NaverSise(collectDay
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))));
                    } catch (StringIndexOutOfBoundsException e) {
                        log.info("companycode not exist : {} " , request.getCompanyCode());
                    }
                }
                count++;
            }

            NaverSise tmpNaver = dayprice.stream()
                    .min(Comparator.comparing(NaverSise::getCollectDay))
                    .get();

            if (LocalDate.parse(tmpNaver.getCollectDay()).isBefore(now.minusDays(request.getLimitDays()))) {
                break parserLoop;
            }
        }

        final Collector<NaverSise, Summary, Summary> collector = new SiseSummaryCollector();
        final Map<String, Summary> map = dayprice.stream()
                .filter(x->LocalDate.parse(x.getCollectDay()).isAfter(now.minusDays(request.getLimitDays())))
                .collect(Collectors.groupingBy(o -> o.getCollectDay(), collector));
        return map;
    }
}
