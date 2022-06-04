package com.sparta.week5home.service;

import com.sparta.week5home.dto.FoodDto;
import com.sparta.week5home.domain.Food;
import com.sparta.week5home.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    // 음식 등록
    @Transactional
    public void register(Long restaurantId, List<FoodDto> requestDtoList) {

        for(FoodDto requestDto:requestDtoList){
            // 기등록 음식 확인
            if(foodRepository.findByRestaurantIdAndName(restaurantId, requestDto.getName())!=null){
                throw new DuplicateFormatFlagsException("이미 존재하는 메뉴입니다.");
            } else if(requestDto.getPrice()<100 || requestDto.getPrice() > 1000000 || requestDto.getPrice() % 100 !=0){
                throw new IllegalArgumentException("음식 요금 입력값이 바르지 않습니다.");
            }

            // DB에 음식 정보 저장
            Food food = new Food(restaurantId, requestDto);
            foodRepository.save(food);
        }
    }

    public List<Food> getFoods(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
