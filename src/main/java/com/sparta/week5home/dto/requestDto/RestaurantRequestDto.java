package com.sparta.week5home.dto.requestDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
