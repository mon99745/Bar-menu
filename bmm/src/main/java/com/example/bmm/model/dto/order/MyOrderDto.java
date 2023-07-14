package com.example.bmm.model.dto.order;

import com.example.bmm.model.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MyOrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String representativeImage;
    private String representativeProductName;
    private int totalAmount;
    private OrderStatus orderStatus;

    @Builder
    public MyOrderDto(Long orderId, LocalDateTime orderDate,
                      String representativeImage, String representativeProductName,
                      int totalAmount, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.representativeImage = representativeImage;
        this.representativeProductName = representativeProductName;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
