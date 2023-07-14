package com.example.bmm.domain.order;

import com.example.bmm.model.dto.order.OrderSummaryDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderCustomRepository {
    OrderSummaryDto getOrderSummaryInCart(Authentication authentication, Long userId, List<Long> productIdList);
}
