package com.sparta.week5home.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderedFoodDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
