package danta.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepository extends JpaRepository<Order, Long>, MyOrderCustomRepository{
}
