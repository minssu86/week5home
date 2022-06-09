package com.sparta.week5home.controller;

import com.sparta.week5home.dto.requestDto.LoginRequestDto;
import com.sparta.week5home.dto.requestDto.SignupRequestDto;
import com.sparta.week5home.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // 로그인
    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto);
    }

    // 회원 가입
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

}
