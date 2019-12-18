package com.issuefinder.crawling.service;

import com.google.common.collect.Lists;
import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.PaxnetRank;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static com.issuefinder.crawling.model.vo.ReferType.PAXNET;
import static com.issuefinder.crawling.model.vo.ResourceType.ARTICLE;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class PaxnetRankCrawler extends JsoupService implements Crawlerable {

    @Value("${crawler-url.paxent}")
    private String paxent;

    @Override
    public CrawlerDto parser(String companyCode) {

        Document doc = getJsuup(paxent);
        Elements elements = doc.select(".list-box tbody tr a");

        List<PaxnetRank> paxlist = new ArrayList<>();
        ListIterator<String> list = Lists.newArrayList(
                "realtime", "accumulation", "community", "attention").listIterator();
        String type = list.next();

        int rank = 0;
        int point = 50;
        for (Element element : elements) {
            String href = element.attr("href");
            int length = href.length();
            String parseCompanyCode = href.substring(length - 6, length);
            paxlist.add(new PaxnetRank(parseCompanyCode, point, type));

            rank++;
            point--;
            if (rank % 50 == 0) {
                if (list.hasNext()) {
                    type = list.next();
                }
                point = 50;
            }
        }

        Map<String, Integer> result = paxlist.stream()
                .collect(groupingBy(PaxnetRank::getCompanyCode, summingInt(PaxnetRank::getPoint)));

        CrawlerDto crawler = CrawlerDto.builder()
                .companyCode(companyCode)
                .refer(PAXNET.name())
                .resourceType(ARTICLE.getCode())
                .crawlerList(result)
                .build();

        return crawler;
    }
}
