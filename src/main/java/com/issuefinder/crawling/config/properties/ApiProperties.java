package com.issuefinder.crawling.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = ApiProperties.PREFIX)
public class ApiProperties {
    public static final String PREFIX = "api.dart";
    private String scheme;
    private String host;
    private String key;
}
