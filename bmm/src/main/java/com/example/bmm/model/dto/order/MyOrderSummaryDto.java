package com.example.bmm.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MyOrderSummaryDto {
    private List<MyOrderDto> myOrderList;
    private int total;
}
