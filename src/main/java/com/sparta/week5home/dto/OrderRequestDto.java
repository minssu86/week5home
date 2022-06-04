package com.sparta.week5home.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRequestDto {
    private final Long restaurantId;
    private final List<FoodOrderRequestDto> foods;
}
