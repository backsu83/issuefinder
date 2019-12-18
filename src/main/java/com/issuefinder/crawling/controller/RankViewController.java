package com.issuefinder.crawling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankViewController {

    @GetMapping("/index")
    public String amcharts() {
        return "index";
    }
}
