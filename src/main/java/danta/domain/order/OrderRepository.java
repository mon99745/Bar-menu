package danta.domain.order;

import danta.model.dto.order.OrderSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long>, OrderCustomRepository {
}