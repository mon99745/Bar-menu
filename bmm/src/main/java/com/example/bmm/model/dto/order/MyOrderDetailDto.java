package com.example.bmm.model.dto.order;

import com.example.bmm.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MyOrderDetailDto {
    private LocalDateTime orderDate;
    private Long orderId;
    private List<MyOrderDetailProductDto> orderedProductList;
    private MyOrderDetailReceiverDto receiverInfoDto;
    private OrderStatus orderStatus;
}

