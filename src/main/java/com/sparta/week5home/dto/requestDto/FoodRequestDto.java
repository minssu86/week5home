package com.sparta.week5home.dto.requestDto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FoodRequestDto {
    private final String name;
    private final int price;
}
