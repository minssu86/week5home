package com.sparta.week5home.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class OrderedFood {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 주문 번호
    @Column(nullable = false)
    private Long orderId;

    // 메뉴 번호
    @Column(nullable = false)
    private String foodName;

    // 메뉴 수량
    @Column(nullable = false)
    private int quantity;

    public OrderedFood(Long orderId, String foodName, int quantity){
        this.orderId = orderId;
        this.foodName = foodName;
        this.quantity = quantity;
    }

}


