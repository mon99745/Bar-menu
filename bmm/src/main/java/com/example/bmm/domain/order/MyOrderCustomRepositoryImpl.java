package com.example.bmm.domain.order;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.example.bmm.domain.order.QOrder.order;

@Repository
public class MyOrderCustomRepositoryImpl implements MyOrderCustomRepository{
    private JPAQueryFactory query;

    public MyOrderCustomRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Order> getMyOrders(Long ordererId, Pageable pageable) {
        QueryResults<Order> searchOrderByOrdererId = query.select(order)
                .from(order)
//                .join(order.ordererId, user.id).fetchJoin()
                .join(order).fetchJoin()
                .where(order.removed.eq(false))
                .where(order.ordererId.eq(ordererId))
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
//                .join(QOrder.order.ordererId).fetchJoin()
                .join(QOrder.order).fetchJoin()
                .where(QOrder.order.orderId.eq(orderId))
                .fetchOne();

        return Optional.of(order);
    }
}