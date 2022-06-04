package com.sparta.week5home.controller;

import com.sparta.week5home.dto.OrderRequestDto;
import com.sparta.week5home.dto.OrderResponseDto;
import com.sparta.week5home.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {

    @Autowired
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto orderFoods(@RequestBody OrderRequestDto requestDto){
        return orderService.orderFoods(requestDto);
    }

    @GetMapping("orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }


}
