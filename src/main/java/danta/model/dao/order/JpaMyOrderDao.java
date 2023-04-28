package danta.model.dao.order;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import danta.domain.order.Order;
import danta.domain.order.QOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static danta.domain.order.QOrder.order;
import static danta.domain.user.QUser.user;


@Repository
public class JpaMyOrderDao implements MyOrderDao {
    private JPAQueryFactory query;

    public JpaMyOrderDao(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Order> getMyOrders(Long ordererId, Pageable pageable) {
        QueryResults<Order> searchOrderByOrdererId = query.select(order)
                .from(order)
                .join(order.orderer, user).fetchJoin()
                .join(order).fetchJoin()
                .where(order.removed.eq(false))
                .where(order.orderer.id.eq(ordererId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.orderId.desc())
                .fetchResults();

        List<Order> contents = searchOrderByOrdererId.getResults();
        long total = searchOrderByOrdererId.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    @Override
    public Optional<Order> getMyOrderDetails(Long orderId) {
        Order order = query.select(QOrder.order)
                .from(QOrder.order)
                .join(QOrder.order.orderer).fetchJoin()
                .join(QOrder.order).fetchJoin()
                .where(QOrder.order.orderId.eq(orderId))
                .fetchOne();

        return Optional.of(order);
    }
}

