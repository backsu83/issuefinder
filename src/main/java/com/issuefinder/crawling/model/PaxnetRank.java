package com.issuefinder.crawling.model;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaxnetRank {

    private String companyCode;
    private int point;
    private int rank;
    private String type;

    public PaxnetRank(String companyCode, int rank, String type) {
        this.companyCode = companyCode;
        this.rank = rank;
        this.type = type;
        this.point = calculrate();
    }

    public int calculrate() {
        if(getType().equals("realtime")) {
            this.point = getRank() * 20;
        } else if (getType().equals("accumulation")) {
            this.point = getRank() * 10;
        } else if (getType().equals("attention")) {
            this.point = getRank() * 5;
        } else {
            this.point = 0;
        }
        return point;
    }

}
