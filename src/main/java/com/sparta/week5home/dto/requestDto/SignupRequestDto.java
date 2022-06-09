package com.sparta.week5home.dto.requestDto;

import com.sparta.week5home.model.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequestDto {

    private String username;
    private String password;
    private UserRoleEnum role;

}
