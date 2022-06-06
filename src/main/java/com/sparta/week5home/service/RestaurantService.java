package com.sparta.week5home.service;

import com.sparta.week5home.dto.RestaurantDto;
import com.sparta.week5home.domain.Restaurant;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    // 음식점 등록
    public Restaurant register(RestaurantDto requestDto) {
        // 최소 주문 판별 (내장 함수 사용방법 스터디 필요)
        if(requestDto.getMinOrderPrice()<1000 || requestDto.getMinOrderPrice()>100000 || requestDto.getMinOrderPrice()%100 > 0){
            throw new IllegalArgumentException("최소주문 입력값이 바르지 않습니다.");
        // 기본 배달비 판별 (내장 함수 사용방법 스터디 필요)
        }else if(requestDto.getDeliveryFee()<0 || requestDto.getDeliveryFee()>10000 || requestDto.getDeliveryFee()%500 > 0){
            throw new IllegalArgumentException("기본 배달비 입력값이 바르지 않습니다.");
        }
        // 음식점 Entity 생성
        Restaurant restaurant = new Restaurant(requestDto);
        return   restaurantRepository.save(restaurant);
    }

    @Transactional
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}


