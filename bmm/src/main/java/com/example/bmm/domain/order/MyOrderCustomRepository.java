package com.example.bmm.domain.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface MyOrderCustomRepository {

    Page<Order> getMyOrders(Long ordererId, Pageable pageable);
    Optional<Order> getMyOrderDetails(Long orderId);
}
