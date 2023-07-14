package com.example.bmm.model.dto.cart;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ModifyOrderCountRequestFormDto {
    private Long productId;
    @Min(1)
    private int orderCount;
}
