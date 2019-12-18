package com.issuefinder.crawling.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NaverArticle {

    private String collectDay;
    private int views;
    private int sympathy;
    private int unsympathy;
    private int totalCount;

    public NaverArticle(String collectDay , int totalCount) {
        this.collectDay = collectDay;
        this.totalCount = totalCount;
    }

    public NaverArticle(String collectDay, int views, int sympathy, int unsympathy) {
        this.collectDay = collectDay;
        this.views = views;
        this.sympathy = sympathy;
        this.unsympathy = unsympathy;
        this.totalCount = getViews() + ((getSympathy() + getUnsympathy()) * 10);
    }

}
