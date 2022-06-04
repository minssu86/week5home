package com.sparta.week5home.domain;

import com.sparta.week5home.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 음식 이름
    @Column(nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private int price;

    // 음식점 ID
    @Column(nullable = false)
    private Long restaurantId;


    public Food(Long restaurantId, FoodDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
