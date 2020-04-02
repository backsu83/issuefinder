package com.issuefinder.crawling.controller.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String username;
    private String email;
    private String phone;
    private String birthday;
    private String gender;
}
