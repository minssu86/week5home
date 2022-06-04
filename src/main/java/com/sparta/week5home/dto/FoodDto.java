package com.sparta.week5home.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FoodDto {
    private final String name;
    private final int price;
}
