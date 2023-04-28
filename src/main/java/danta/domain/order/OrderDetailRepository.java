package danta.domain.order;


import danta.domain.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {
}