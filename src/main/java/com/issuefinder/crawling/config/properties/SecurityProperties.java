package com.issuefinder.crawling.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {
    public static final String PREFIX = "spring.jwt.token";
    private String secretKey;
    private String expireLength;

}
