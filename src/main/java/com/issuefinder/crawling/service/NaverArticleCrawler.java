package com.issuefinder.crawling.service;

import com.issuefinder.crawling.config.properties.HostProperties;
import com.issuefinder.crawling.controller.req.CrawlerRequest;
import com.issuefinder.crawling.model.NaverArticle;
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

@Slf4j
@Component
public class NaverArticleCrawler extends JsoupService implements Crawlerable {

    @Autowired
    private HostProperties host;

    @Override
    public Map<String, Summary> parser(CrawlerRequest request) {

        List<NaverArticle> naverlist = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        LocalDate now = LocalDate.now();

        for (LocalDate start = now; start.isAfter(now.minusDays(request.getLimitDays())); start = start.minusDays(1)) {
            naverlist.add(new NaverArticle(start.toString(), 0, 0, 0));
        }

        parserLoop:
        for (int i = 0; i < 200; i++) {
            Document doc = getJsuup(host.getNaverArticle() + "?code=" + request.getCompanyCode() + "&page=" + i);
            Elements elements = doc.select("tbody tr td .tah.p10");
            int count = 1;

            for (Element element : elements) {
                queue.add(element.text());
                if (count % 4 == 0) {
                    try {
                        String collectDay = queue.pop().substring(0, 10).replace(".", "-");
                        naverlist.add(new NaverArticle(collectDay
                                , Integer.valueOf(queue.pop())
                                , Integer.valueOf(queue.pop())
                                , Integer.valueOf(queue.pop())));
                    } catch (StringIndexOutOfBoundsException e) {
                        log.info("companycode not exist : {} ", request.getCompanyCode());
                    }
                }
                count++;
            }

            NaverArticle tmpNaver = naverlist.stream()
                    .min(Comparator.comparing(NaverArticle::getCollectDay))
                    .get();

            if (LocalDate.parse(tmpNaver.getCollectDay()).isBefore(now.minusDays(request.getLimitDays()))) {
                break parserLoop;
            }
        }

        final Collector<NaverArticle, Summary, Summary> collector = new ArticleSummartCollector();
        final Map<String, Summary> map = naverlist.stream()
                .filter(x->LocalDate.parse(x.getCollectDay()).isAfter(now.minusDays(request.getLimitDays())))
                .collect(Collectors.groupingBy(o -> o.getCollectDay(), collector));
        return map;
    }
}
