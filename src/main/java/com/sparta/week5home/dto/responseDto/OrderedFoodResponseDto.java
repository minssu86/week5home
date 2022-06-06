package com.sparta.week5home.dto.responseDto;

import com.sparta.week5home.domain.OrderedFood;
import lombok.Getter;

@Getter
public class OrderedFoodResponseDto {

    private String name;

    private int quantity;

    private int price;

//    public OrderedFoodResponseDto(String name, int quantity, int price){
//        this.name = name;
//        this.quantity = quantity;
//        this.price = price;
//    }

    public OrderedFoodResponseDto(OrderedFood orderedFood){
        this.name = orderedFood.getFoodName();
        this.quantity = orderedFood.getQuantity();
        this.price = orderedFood.getPrice();
    }

}