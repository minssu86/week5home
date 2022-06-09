package com.sparta.week5home.service;

import com.sparta.week5home.dto.requestDto.RestaurantRequestDto;
import com.sparta.week5home.model.restaurantDomain.Restaurant;
import com.sparta.week5home.dto.responseDto.RestaurantResponseDto;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    // 음식점 등록
    @Transactional
    public RestaurantResponseDto register(RestaurantRequestDto requestDto) {
        // 최소 주문 판별 (내장 함수 사용방법 스터디 필요)
        if(requestDto.getMinOrderPrice()<1000 || requestDto.getMinOrderPrice()>100000 || requestDto.getMinOrderPrice()%100 > 0){
            throw new IllegalArgumentException("최소주문 입력값이 바르지 않습니다.");
        // 기본 배달비 판별 (내장 함수 사용방법 스터디 필요)
        }else if(requestDto.getDeliveryFee()<0 || requestDto.getDeliveryFee()>10000 || requestDto.getDeliveryFee()%500 > 0){
            throw new IllegalArgumentException("기본 배달비 입력값이 바르지 않습니다.");
        }else if(restaurantRepository.findByName(requestDto.getName())!=null){
            throw new IllegalArgumentException("중복된 가게 이름입니다.");
        }

        // 음식점 Entity 생성
        return new RestaurantResponseDto(restaurantRepository.save(new Restaurant(requestDto)));
    }

    // 음식점 목록 조회
    public List<RestaurantResponseDto> getRestaurants() {
        List<RestaurantResponseDto> restaurantResponseDtos = new ArrayList<>();
        for(Restaurant restaurant : restaurantRepository.findAll()){
            restaurantResponseDtos.add(new RestaurantResponseDto(restaurant));
        }

        return restaurantResponseDtos;
    }
}


