package com.example.bmm.model.dto.cart;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddToCartRequestFormDto {
    private Long productId;

    @Min(1)
    private Integer orderCount;
}

