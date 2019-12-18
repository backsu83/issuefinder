package com.issuefinder.crawling.service;

import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.NaverArticle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.issuefinder.crawling.model.vo.ReferType.NAVER;
import static com.issuefinder.crawling.model.vo.ResourceType.ARTICLE;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class NaverArticleCrawler extends JsoupService implements Crawlerable {

    public static final long LIMIT_DAY = 14L;

    @Value("${crawler-url.naver}")
    private String naver;

    @Override
    public CrawlerDto parser(String companyCode) {

        List<NaverArticle> naverlist = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        LocalDate now = LocalDate.now();

        for (LocalDate start = now; start.isAfter(now.minusDays(LIMIT_DAY)); start = start.minusDays(1)) {
            naverlist.add(new NaverArticle(start.toString(), 1));
        }

        parserLoop:
        for (int i = 0; i < 100; i++) {
            Document doc = getJsuup("https://finance.naver.com/item/board.nhn?code=" + companyCode + "&page=" + i);
            Elements elements = doc.select("tbody tr td .tah.p10");
            int count = 1;

            for (Element element : elements) {
                queue.add(element.text());
                if (count % 4 == 0) {
                    String collectDay = queue.pop().substring(0, 10).replace(".", "-");
                    naverlist.add(new NaverArticle(collectDay
                            , Integer.valueOf(queue.pop())
                            , Integer.valueOf(queue.pop())
                            , Integer.valueOf(queue.pop())));
                }
                count++;
            }

            NaverArticle tmpNaver = naverlist.stream()
                    .min(Comparator.comparing(NaverArticle::getCollectDay))
                    .get();

            if (LocalDate.parse(tmpNaver.getCollectDay()).isBefore(now.minusDays(LIMIT_DAY))) {
                break parserLoop;
            }
        }

        Map<String, Integer> result = naverlist.stream()
                .collect(groupingBy(NaverArticle::getCollectDay, summingInt(NaverArticle::getTotalCount)));

        CrawlerDto crawler = CrawlerDto.builder()
                .companyCode(companyCode)
                .refer(NAVER.name())
                .resourceType(ARTICLE.getCode())
                .crawlerList(result)
                .build();

        return crawler;
    }
}
