package com.sparta.week5home.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
