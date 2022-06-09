package com.sparta.week5home.service;

import com.sparta.week5home.model.restaurantDomain.Food;
import com.sparta.week5home.model.restaurantDomain.Order;
import com.sparta.week5home.model.restaurantDomain.OrderedFood;
import com.sparta.week5home.model.restaurantDomain.Restaurant;
import com.sparta.week5home.dto.requestDto.OrderFoodRequestDto;
import com.sparta.week5home.dto.requestDto.OrderRequestDto;
import com.sparta.week5home.dto.responseDto.OrderResponseDto;
import com.sparta.week5home.dto.responseDto.OrderedFoodResponseDto;
import com.sparta.week5home.repository.FoodRepository;
import com.sparta.week5home.repository.OrderRepository;
import com.sparta.week5home.repository.OrderedFoodRepository;
import com.sparta.week5home.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedFoodRepository orderedFoodRepository;
    private final RestaurantRepository restaurantRepository;
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

        // 주문서 작성
        Order order = new Order(restaurant);
        orderRepository.save(order);

        // 전체 주문 음식 비용 확인용
        int totalFoodPrice = 0;

        // response 용 주문 목록
        List<OrderedFoodResponseDto> orderedFoodResponseDtos = new ArrayList<>();

        // DB에 주문 정보 저장
        for(OrderFoodRequestDto orderFoodRequestDto : requestDto.getFoods()){
            // 음식점 ID와, 요리 ID로 요리정보 불러오기
            Food food = foodRepository.findByRestaurantIdAndId(requestDto.getRestaurantId(), orderFoodRequestDto.getId());

            // 유효성 검사 : 수량 확인
            if(orderFoodRequestDto.getQuantity()>100 || orderFoodRequestDto.getQuantity()<0){
                throw new IllegalArgumentException("주문 수량이 가능 범위를 초과 하였습니다.");
            }

            // 주문 받은 요리 목록 저장
            OrderedFood orderedFood = new OrderedFood(
                    order,
                    food.getName(),
                    orderFoodRequestDto.getQuantity(),
                    food.getPrice()* orderFoodRequestDto.getQuantity()
            );

            // DB 저장
            orderedFoodRepository.save(orderedFood);

            // Response 용 요리 목록
            OrderedFoodResponseDto orderedFoodResponseDto = new OrderedFoodResponseDto(orderedFood);
            orderedFoodResponseDtos.add(orderedFoodResponseDto);

            // 전체 요리 비용 연산
            totalFoodPrice += orderedFoodResponseDto.getPrice();
        }

        // 유효성 검사 : 최소 주문 금액
        if(totalFoodPrice <= restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("주문 금액이 작습니다");
        }

        // 주문서 최종 작성(totalPrice 추가)
        order.saveTotalPrice(totalFoodPrice + restaurant.getDeliveryFee());

        return OrderResponseDto.builder()
                .restaurantName(order.getRestaurant().getName())
                .foods(orderedFoodResponseDtos)
                .deliveryFee(order.getRestaurant().getDeliveryFee())
                .totalPrice(totalFoodPrice + restaurant.getDeliveryFee())
                .build();
    }


    // 전체 주문 목록 조회
    public List<OrderResponseDto> getOrders() {

        // 전체 주문서 가져오기
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        List<OrderedFoodResponseDto> orderedFoodResponseDtos = new ArrayList<>();

        OrderResponseDto orderResponseDto = OrderResponseDto.builder().build();
        for(Order order : orders){
            Restaurant restaurant = restaurantRepository.findByName(order.getRestaurant().getName());

            List<OrderedFood> orderedFoods = orderedFoodRepository.findAllByOrderId(order.getId());
            for(OrderedFood orderedFood : orderedFoods){
                Food food = foodRepository.findByRestaurantIdAndName(restaurant.getId(),orderedFood.getFoodName());
                OrderedFoodResponseDto orderedFoodResponseDto = new OrderedFoodResponseDto(orderedFood);
                orderedFoodResponseDtos.add(orderedFoodResponseDto);
            }

            orderResponseDto.setFoods(orderedFoodResponseDtos);
            orderResponseDto.setRestaurantName(order.getRestaurant().getName());
            orderResponseDto.setDeliveryFee(order.getRestaurant().getDeliveryFee());
            orderResponseDto.setTotalPrice(order.getTotalPrice());

            orderResponseDtos.add(orderResponseDto);
        }

        return orderResponseDtos;
    }
}
