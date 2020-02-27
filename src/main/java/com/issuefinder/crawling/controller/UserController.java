package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.req.UserRequest;
import com.issuefinder.crawling.controller.res.UserResponse;
import com.issuefinder.crawling.model.entity.User;
import com.issuefinder.crawling.service.UserService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    public String login(@ApiParam("Username") @RequestParam String username,
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    public String signup(@ApiParam("Signup User") @RequestBody UserRequest user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @DeleteMapping(value = "/{username}")
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    public UserResponse search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponse.class);
    }

    @GetMapping(value = "/me")
    public UserResponse whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponse.class);
    }

    @GetMapping("/refresh")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
