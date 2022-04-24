package danta.model.dao.order;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.domain.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMyOrderDao implements MyOrderDao {
    private JPAQueryFactory query;

    public JpaMyOrderDao(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<OrderEntity> getMyOrders(Long ordererId, Pageable pageable) {
        QueryResults<OrderEntity> searchOrderByOrdererId = query.select(orderEntity)
                .from(orderEntity)
                .join(orderEntity.orderer, User).fetchJoin()
                .join(orderEntity.deliveryInformation, deliveryEntity).fetchJoin()
                .where(orderEntity.removed.eq(false))
                .where(orderEntity.orderer.memberId.eq(ordererId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderEntity.orderId.desc())
                .fetchResults();

        List<OrderEntity> contents = searchOrderByOrdererId.getResults();
        long total = searchOrderByOrdererId.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Optional<OrderEntity> getMyOrderDetails(Long orderId) {
        OrderEntity orderEntity = query.select(QOrderEntity.orderEntity)
                .from(QOrderEntity.orderEntity)
                .join(QOrderEntity.orderEntity.orderer).fetchJoin()
                .join(QOrderEntity.orderEntity.deliveryInformation).fetchJoin()
                .where(QOrderEntity.orderEntity.orderId.eq(orderId))
                .fetchOne();

        return Optional.of(orderEntity);
    }
}

