package danta.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long>, OrderCustomRepository {

//    List<Order> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}