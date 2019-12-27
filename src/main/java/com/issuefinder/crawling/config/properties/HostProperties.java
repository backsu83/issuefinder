package com.issuefinder.crawling.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = HostProperties.PREFIX)
public class HostProperties {
    public static final String PREFIX = "spring.host";
    private String paxnet;
    private String naverArticle;
    private String naverPrice;
}
