package com.sparta.week5home.repository;

import com.sparta.week5home.model.restaurantDomain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByName(String restaurantName);
}
