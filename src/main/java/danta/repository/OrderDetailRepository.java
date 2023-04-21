package danta.repository;


import danta.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {
}