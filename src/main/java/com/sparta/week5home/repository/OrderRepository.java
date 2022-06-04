package com.sparta.week5home.repository;

import com.sparta.week5home.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Long findFirstByOrderByIdDesc();
}
