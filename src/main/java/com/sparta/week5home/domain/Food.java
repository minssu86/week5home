package com.sparta.week5home.domain;

import com.sparta.week5home.dto.requestDto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 음식 이름
    @Column(nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private int price;

    // 음식점 ID    Join 사용할 필요가 있을까??
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;


    public Food(Restaurant restaurant, FoodRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }
}
