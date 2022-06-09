package com.sparta.week5home.model.restaurantDomain;

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

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // 주문 번호

    @Column(nullable = false)
    private String foodName;  // 음식명

    @Column(nullable = false)
    private int quantity;  // 메뉴 수량

    @Column(nullable = false)
    private int price;  // 메뉴 가격 (수량 * 음식 가격)

    public OrderedFood(Order order, String foodName, int quantity, int price){
        this.order = order;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

}


