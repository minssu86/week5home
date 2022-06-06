package com.sparta.week5home.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class OrderedFood {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 주문 번호
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // 음식명
    @Column(nullable = false)
    private String foodName;

    // 메뉴 수량
    @Column(nullable = false)
    private int quantity;

    // 메뉴 수량 * 메뉴 가격
    @Column(nullable = false)
    private int price;

    public OrderedFood(Order order, String foodName, int quantity){
        this.order = order;
        this.foodName = foodName;
        this.quantity = quantity;
    }

}


