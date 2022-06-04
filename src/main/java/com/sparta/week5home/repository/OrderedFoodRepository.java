package com.sparta.week5home.repository;

import com.sparta.week5home.domain.OrderedFood;
import com.sparta.week5home.dto.OrderedFoodDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedFoodRepository extends JpaRepository<OrderedFood, Long> {
    List<OrderedFood> findAllByOrderId(Long id);
}
