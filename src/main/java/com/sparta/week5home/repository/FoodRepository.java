package com.sparta.week5home.repository;

import com.sparta.week5home.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findByRestaurantIdAndId(Long restaurantId, Long id);

    List<Food> findAllByRestaurantId(Long restaurantId);

    Food findByRestaurantIdAndName(Long restaurantId, String foodName);
}
