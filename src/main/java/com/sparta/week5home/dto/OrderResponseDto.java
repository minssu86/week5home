package com.sparta.week5home.dto;

import com.sparta.week5home.domain.OrderedFood;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderedFood> foods;
    private int deliveryFee;
    private int totalPrice;
}
