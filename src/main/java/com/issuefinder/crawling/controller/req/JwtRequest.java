package com.issuefinder.crawling.controller.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;

}
