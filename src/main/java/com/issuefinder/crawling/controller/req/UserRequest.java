package com.issuefinder.crawling.controller.req;


import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private String email;

    @Size(min = 8, message = "비밀번호는 8자리 이상으로 입력해 주시기 바랍니다.")
    private String password;

    private String username;

    @Pattern(regexp = "[FM]" , message = "성별을 올바른 형식으로 입력해 주시기 바랍니다.")
    private String gender;
    private String phone;

    @Size(min = 8, max=8 , message = "생년월일을 올바른 형식으로 입력해 주시기 바랍니다.")
    @Pattern(regexp = "^[0-9]+$")
    private String birthday;

}
