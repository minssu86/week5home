package com.sparta.week5home.domain;

import com.sparta.week5home.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 음식점 이름
    @Column(nullable = false)
    private String name;


    // 최소 주문 가격
    // 허용값 : 1,000원 ~ 100,000원
    // 100 원 단위로만 입력 가능
    // 허용값이 아니거나 100운 단위 입력이 아닌 경우 에러 발생
    @Column(nullable = false)
    private int minOrderPrice;


    // 기본 배달비
    // 허용값 : 0원 ~ 10,000원
    // 500원 단위로 만 입력 가능능
    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RestaurantDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}
