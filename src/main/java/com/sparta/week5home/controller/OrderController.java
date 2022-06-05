package com.sparta.week5home.controller;

import com.sparta.week5home.dto.OrderRequestDto;
import com.sparta.week5home.dto.OrderResponseDto;
import com.sparta.week5home.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // 주문하기
    @PostMapping("/order/request")
    public OrderResponseDto orderFoods(@RequestBody OrderRequestDto requestDto){ return orderService.orderFoods(requestDto);}

    // 전체 주문 조회
    @GetMapping("orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}
