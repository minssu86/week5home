package com.sparta.week5home.service;

import com.sparta.week5home.domain.Restaurant;
import com.sparta.week5home.dto.FoodDto;
import com.sparta.week5home.domain.Food;
import com.sparta.week5home.repository.FoodRepository;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void register(Long restaurantId, List<FoodDto> requestDtoList) {
        // 식당 정보 불러오기
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new NullPointerException("존재 하지 않는 식당 입니다.")
        );

        for(FoodDto requestDto:requestDtoList){
            // 기등록 음식 확인
            if(foodRepository.findByRestaurantIdAndName(restaurantId, requestDto.getName())!=null){
                throw new DuplicateFormatFlagsException("이미 존재하는 메뉴입니다.");
            } else if(requestDto.getPrice()<100 || requestDto.getPrice() > 1000000 || requestDto.getPrice() % 100 !=0){
                throw new IllegalArgumentException("음식 요금 입력값이 바르지 않습니다.");
            }

            // DB에 음식 정보 저장
            Food food = new Food(restaurant, requestDto);
            foodRepository.save(food);

        }
    }

    public List<Food> getFoods(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
