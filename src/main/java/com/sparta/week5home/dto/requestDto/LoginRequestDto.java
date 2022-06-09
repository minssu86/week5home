package com.sparta.week5home.dto.requestDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {

    private final String username;
    public final String password;

}
