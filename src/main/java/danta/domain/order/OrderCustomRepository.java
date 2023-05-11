package danta.domain.order;

import danta.model.dto.order.OrderSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface OrderCustomRepository {
    OrderSummaryDto getOrderSummaryInCart(Authentication authentication, Long userId, List<Long> productIdList);
}
