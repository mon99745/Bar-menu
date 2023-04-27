package danta.repository;

import danta.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
    Order findOrderById(Long userId);
}