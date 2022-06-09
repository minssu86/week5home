package com.sparta.week5home.model.restaurantDomain;

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

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;  // 음식점 ID

    @Column(nullable = false)
    private int totalPrice;  // 주문 총 비용

    public Order(Restaurant restaurant){
        this.restaurant=restaurant;
        this.totalPrice=0;
    }

    public void saveTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}

