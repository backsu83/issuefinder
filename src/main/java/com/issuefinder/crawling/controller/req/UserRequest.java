package com.issuefinder.crawling.controller.req;


import com.issuefinder.crawling.contants.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String password;
    @ApiModelProperty(position = 3)
    List<Role> roles;

}
