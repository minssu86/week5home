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

    // 메뉴 번호
    @Column(nullable = false)
    private String foodName;

    // 메뉴 수량
    @Column(nullable = false)
    private int quantity;

    public OrderedFood(Order order, String foodName, int quantity){
        this.order = order;
        this.foodName = foodName;
        this.quantity = quantity;
    }

}


