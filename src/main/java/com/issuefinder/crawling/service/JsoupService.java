package com.issuefinder.crawling.service;

import com.issuefinder.crawling.exception.JsoupConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
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
}
