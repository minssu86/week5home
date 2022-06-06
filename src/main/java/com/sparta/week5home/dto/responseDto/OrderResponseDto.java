package com.sparta.week5home.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderedFoodResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
