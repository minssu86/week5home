package com.sparta.week5home.dto.responseDto;

import com.sparta.week5home.model.restaurantDomain.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private final Long id;
    private final String name;
    private final int price;

    public FoodResponseDto(Food food){
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }

}
