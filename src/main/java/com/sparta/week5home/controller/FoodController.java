package com.sparta.week5home.controller;

import com.sparta.week5home.dto.requestDto.FoodRequestDto;
import com.sparta.week5home.dto.responseDto.FoodResponseDto;
import com.sparta.week5home.model.UserRoleEnum;
import com.sparta.week5home.service.FoodService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    // 음식 등록
    @Secured(UserRoleEnum.Authority.ADMIN)  // ADMIN 권한을 가진 유저에게만 접근 허용
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDtoList){
        foodService.register(restaurantId, requestDtoList);
    }

    // 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

}
