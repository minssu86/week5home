package com.sparta.week5home.repository;

import com.sparta.week5home.model.restaurantDomain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Long findByRestaurantName(String restaurantName);
}
