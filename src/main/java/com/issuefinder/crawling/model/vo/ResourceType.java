package com.issuefinder.crawling.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceType {

    ARTICLE(1001,"게시글"),
    MEDIA(1002,"매체"),
    RANKING(1003,"랭킹사이트"),
    SISE(1004,"시세");

    private Integer code;
    private String name;

}
