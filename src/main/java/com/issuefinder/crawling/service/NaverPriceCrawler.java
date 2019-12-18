package com.issuefinder.crawling.service;

import com.issuefinder.crawling.model.CrawlerDto;
import com.issuefinder.crawling.model.NaverDayPrice;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Locale.US;

class NaverPriceCrawler extends JsoupService implements Crawlerable {

    public static final long LIMIT_DAY = 14L;

    @Override
    public CrawlerDto parser(String companyCode) {

        List<NaverDayPrice> dayprice = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
        LocalDate now = LocalDate.now();

        parserLoop:
        for (int i = 0; i < 5; i++) {
            Document doc = getJsuup("https://finance.naver.com/item/sise_day.nhn?code=" + companyCode + "&page=" + i);
            Elements elements = doc.select("tbody tr td .tah");
            int count = 1;

            for (Element element : elements) {
                queue.add(element.text());
                if (count % 7 == 0) {
                    String collectDay = queue.pop().substring(0, 10).replace(".", "-");
                        dayprice.add(new NaverDayPrice(collectDay
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))
                                , Integer.parseInt(queue.pop().replaceAll(",", ""))));
                }
                count++;
            }
        }

        System.out.println(dayprice.toString());
        return null;
    }
}
