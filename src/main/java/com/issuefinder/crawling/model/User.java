package com.issuefinder.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;

    private String email;
    private String gender;
    private String birthday;
    private String phone;

    private String password;

}
