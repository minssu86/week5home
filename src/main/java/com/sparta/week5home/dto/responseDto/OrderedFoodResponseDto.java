package com.sparta.week5home.dto.responseDto;

import com.sparta.week5home.model.restaurantDomain.OrderedFood;
import lombok.Getter;

@Getter
public class OrderedFoodResponseDto {

    private final String name;

    private final int quantity;

    private final int price;

    public OrderedFoodResponseDto(OrderedFood orderedFood){
        this.name = orderedFood.getFoodName();
        this.quantity = orderedFood.getQuantity();
        this.price = orderedFood.getPrice();
    }

}