package com.example.bmm.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDERED_STATUS("주문완료"), CANCEL_STATUS("주문취소");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
