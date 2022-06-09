package com.sparta.week5home.service;

import com.sparta.week5home.model.restaurantDomain.Restaurant;
import com.sparta.week5home.dto.requestDto.FoodRequestDto;
import com.sparta.week5home.model.restaurantDomain.Food;
import com.sparta.week5home.dto.responseDto.FoodResponseDto;
import com.sparta.week5home.repository.FoodRepository;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository){
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 음식 등록
    @Transactional
    public void register(Long restaurantId, List<FoodRequestDto> requestDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow( // 식당 정보 불러오기
                ()-> new NullPointerException("존재 하지 않는 식당 입니다.")
        );

        for(FoodRequestDto requestDto:requestDtoList){

            if(foodRepository.findByRestaurantIdAndName(restaurantId, requestDto.getName())!=null){  // 기등록 음식 확인
                throw new IllegalArgumentException("이미 존재하는 메뉴입니다.");
            } else if(requestDto.getPrice()<100 || requestDto.getPrice() > 1000000 || requestDto.getPrice() % 100 !=0){
                throw new IllegalArgumentException("음식 요금 입력값이 바르지 않습니다.");
            }

            Food food = new Food(restaurant, requestDto);
            foodRepository.save(food); // DB에 음식 정보 저장
        }
    }

    // 음식 목록 조회
    public List<FoodResponseDto> getFoods(Long restaurantId) {
        List<FoodResponseDto> foodResponseDtos = new ArrayList<>();
        for(Food food : foodRepository.findAllByRestaurantId(restaurantId)){
            foodResponseDtos.add(new FoodResponseDto(food));
        }
        return foodResponseDtos;
    }
}
