package com.issuefinder.crawling.service;

import com.issuefinder.crawling.exception.JsoupConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class JsoupService {

    protected Document getJsuup(String url) {
        try {
            return Optional.ofNullable(Jsoup.connect(url)
                    .timeout(10000)
                    .validateTLSCertificates(false)
                    .get())
                    .orElseThrow(() -> new JsoupConnectionException(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void init(Map<String , Integer> data, long days) {
        LocalDate now = LocalDate.now();
        for (LocalDate start = now; start.isAfter(now.minusDays(days)); start = start.minusDays(1)) {
            data.put(start.toString() , 1);
        }
    }
}
