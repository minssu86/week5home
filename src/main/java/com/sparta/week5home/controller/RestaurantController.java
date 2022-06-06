package com.sparta.week5home.controller;

import com.sparta.week5home.dto.requestDto.RestaurantRequestDto;
import com.sparta.week5home.dto.responseDto.RestaurantResponseDto;
import com.sparta.week5home.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        return restaurantService.register(requestDto);
    }

    // 음식점 조회
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurants(){
        return restaurantService.getRestaurants();
    }

}
