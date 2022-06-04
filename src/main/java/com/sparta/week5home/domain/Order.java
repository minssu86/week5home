package com.sparta.week5home.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "OrderList")
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Order(Restaurant restaurant){
        this.restaurantName=restaurant.getName();
        this.deliveryFee=restaurant.getDeliveryFee();
        this.totalPrice=0;
    }

    public void saveTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

