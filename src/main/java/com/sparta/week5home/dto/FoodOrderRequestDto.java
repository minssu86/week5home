package com.sparta.week5home.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FoodOrderRequestDto {
    private Long id;
    private int quantity;
}
