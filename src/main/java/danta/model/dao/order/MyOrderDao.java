package danta.model.dao.order;

import danta.domain.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MyOrderDao {
    Page<OrderEntity> getMyOrders(Long ordererId, Pageable pageable);
    Optional<OrderEntity> getMyOrderDetails(Long orderId);
}
