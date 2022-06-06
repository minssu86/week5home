package com.sparta.week5home.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "OrderList")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    // 음식점 ID
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;
//    private String restaurantName;

//    @Column(nullable = false)
//    private int deliveryFee;

    // 주문 총 비용
    @Column(nullable = false)
    private int totalPrice;

    public Order(Restaurant restaurant){
        this.restaurant=restaurant;
        this.totalPrice=0;
    }

    public void saveTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

