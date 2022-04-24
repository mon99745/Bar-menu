package danta.model.dao.order;

import danta.model.dto.order.OrderSummaryDto;

import java.util.List;

public interface OrderDao {
    OrderSummaryDto getOrderSummaryInCart(Long memberId, List<Long> itemIdList);
}
