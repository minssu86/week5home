package com.sparta.week5home.dto.responseDto;

import com.sparta.week5home.model.restaurantDomain.Restaurant;
import lombok.Getter;

@Getter
public class RestaurantResponseDto {

    private final Long id;
    private final String name;
    private final int minOrderPrice;
    private final int deliveryFee;

    public RestaurantResponseDto(Restaurant restaurant){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }

}
