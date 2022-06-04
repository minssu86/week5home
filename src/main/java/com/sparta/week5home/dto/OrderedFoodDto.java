package com.sparta.week5home.dto;

import lombok.Getter;

@Getter
public class OrderedFoodDto {

    private String name;

    private int quantity;

    private int price;

    public OrderedFoodDto(String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}