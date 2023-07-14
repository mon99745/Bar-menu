package com.example.bmm.domain.order;


import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {
}