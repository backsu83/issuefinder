package com.issuefinder.crawling.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Summary {

    private int closingPrice;
    private long volume;
    private int views;
    private int sympathy;
    private int unsympathy;
    private int count;

    public void setClosingPrice(int closingPrice) {
        this.closingPrice += closingPrice;
    }

    public void setVolume(long volume) {
        this.volume += volume;
    }

    public void setViews(int views) {
        this.views += views;
    }

    public void setSympathy(int sympathy) {
        this.sympathy += sympathy;
    }

    public void setUnsympathy(int unsympathy) {
        this.unsympathy += unsympathy;
    }

    public void setCount(int count) {
        this.count += count;
    }

    public Summary() {
        this.closingPrice = 0;
        this.volume = 0;
        this.count = 0;
        this.unsympathy = 0;
        this.sympathy = 0;
        this.views = 0;
    }
}
