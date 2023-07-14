package com.example.bmm.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long>, OrderCustomRepository {

//    List<Order> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}