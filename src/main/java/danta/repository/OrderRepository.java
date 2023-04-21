package danta.repository;

import danta.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}