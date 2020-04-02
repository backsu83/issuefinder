package com.issuefinder.crawling.controller;

import com.issuefinder.crawling.controller.req.UserRequest;
import com.issuefinder.crawling.controller.res.StockResponse;
import com.issuefinder.crawling.controller.res.UserResponse;
import com.issuefinder.crawling.model.User;
import com.issuefinder.crawling.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    public String login(@ApiParam("이메일") @RequestParam String username,
                        @ApiParam("패스워드") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @ApiOperation(value="회원 가입 API")
    @PostMapping("/signup")
    public String signup(@ApiParam("Signup User") @RequestBody @Validated UserRequest user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @ApiOperation(value="회원정보 조회 API")
    @GetMapping("/{email}")
    public StockResponse searchId(@ApiParam("이메일") @PathVariable("email") String email) {
        return new StockResponse(userService.search(email));
    }

    @ApiOperation(value="아이디 중복 체크 API")
    @GetMapping("/{email}/exist")
    public StockResponse existByEmail(@ApiParam("이메일") @PathVariable("email") String email) {
        return new StockResponse(userService.exist(email));
    }

    @ApiOperation(value="토큰정보 조회 API")
    @GetMapping(value = "/me")
    public UserResponse whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponse.class);
    }

    @ApiOperation(value="문자 인증")
    @PostMapping("/sms")
    public ResponseEntity authSMS()  {
        userService.authsms();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Deprecated
    @GetMapping("/refresh")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }
}
