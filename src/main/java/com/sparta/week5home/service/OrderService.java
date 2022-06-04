package com.sparta.week5home.service;

import com.sparta.week5home.domain.Food;
import com.sparta.week5home.domain.Order;
import com.sparta.week5home.domain.OrderedFood;
import com.sparta.week5home.domain.Restaurant;
import com.sparta.week5home.dto.FoodOrderRequestDto;
import com.sparta.week5home.dto.OrderRequestDto;
import com.sparta.week5home.dto.OrderResponseDto;
import com.sparta.week5home.repository.FoodRepository;
import com.sparta.week5home.repository.OrderRepository;
import com.sparta.week5home.repository.OrderedFoodRepository;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderedFoodRepository orderedFoodRepository;

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final FoodRepository foodRepository;


    public OrderService(
            OrderRepository orderRepository,
            OrderedFoodRepository orderedFoodRepository,
            RestaurantRepository restaurantRepository,
            FoodRepository foodRepository)
    {
        this.orderRepository = orderRepository;
        this.orderedFoodRepository = orderedFoodRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
    }

    // 주문 데이터 DB 저장
    @Transactional
    public OrderResponseDto orderFoods(OrderRequestDto requestDto) {

        // 가게 정보 불러오기
        // 가게 이름과 배달비, 최소주문 금액 불러오기
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId()).orElseThrow(
                ()-> new NullPointerException("등록되지 않은 가게 입니다.")
        );

        // 주문서 1차 작성(주문서 ID 확보 용)
        Order order = new Order(restaurant);
        orderRepository.save(order);
        Long orderId = orderRepository.findFirstByOrderByIdDesc();
        int totalFoodPrice = 0;
        List<OrderedFood> orderedFoods = new ArrayList<>();
        // DB에 주문 정보 저장
        for(FoodOrderRequestDto foodOrderRequestDto : requestDto.getFoods()){
            // 요리 정보 불러오기
            // 요리 이름과 가격 정보 불러오기
            Food food = foodRepository.findByRestaurantIdAndId(requestDto.getRestaurantId(), foodOrderRequestDto.getId());

            // 주문 받은 요리 목록 저장
            OrderedFood orderedFood = new OrderedFood(orderId, food.getName(), foodOrderRequestDto.getQuantity());
            orderedFoods.add(orderedFood);
            orderedFoodRepository.save(orderedFood);
            totalFoodPrice += food.getPrice()* foodOrderRequestDto.getQuantity();
        }


        if(totalFoodPrice + restaurant.getDeliveryFee()< restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("주문 금액이 작습니다");
        }
        // 주문서 최종 작성(totalPrice 추가)
        order.saveTotalPrice(totalFoodPrice+ restaurant.getDeliveryFee());
        orderRepository.save(order);

        return OrderResponseDto.builder()
                .restaurantName(restaurant.getName())
                .foods(orderedFoods)
                .deliveryFee(restaurant.getDeliveryFee())
                .totalPrice(totalFoodPrice+ restaurant.getDeliveryFee())
                .build();
    }

    public List<OrderResponseDto> getOrders() {

        // 전체 주문서 가져오기
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        OrderResponseDto orderResponseDto = OrderResponseDto.builder().build();
        for(Order order : orders){
            orderResponseDto.setRestaurantName(order.getRestaurantName());
            orderResponseDto.setFoods(orderedFoodRepository.findAllById(order.getId()));
            orderResponseDto.setDeliveryFee(order.getDeliveryFee());
            orderResponseDto.setTotalPrice(order.getTotalPrice());

            orderResponseDtos.add(orderResponseDto);
        }

        return orderResponseDtos;
    }
}
