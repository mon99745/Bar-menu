package danta.domain.order;

import danta.model.dto.order.OrderSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderCustomRepository {
    OrderSummaryDto getOrderSummaryInCart(Long id, List<Long> productIdList);
    Page<Order> getMyOrders(Long ordererId, Pageable pageable);
    Optional<Order> getMyOrderDetails(Long orderId);
}
