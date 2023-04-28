package danta.model.dao.order;

import danta.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MyOrderDao {
    Page<Order> getMyOrders(Long ordererId, Pageable pageable);
    Optional<Order> getMyOrderDetails(Long orderId);
}
