package com.sparta.week5home.dto.requestDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderFoodRequestDto {
    private Long id;
    private int quantity;
}
