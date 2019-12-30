package com.issuefinder.crawling.controller.res;

import com.issuefinder.crawling.contants.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    List<Role> roles;

}
