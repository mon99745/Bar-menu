package danta.domain.order;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.dto.order.OrderProductDto;
import danta.model.dto.order.OrderSummaryDto;
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
public class OrderCustomRepositoryImpl implements OrderCustomRepository{
    private EntityManager em;
    private JPAQueryFactory query;

    public OrderCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public OrderSummaryDto getOrderSummaryInCart(Long id, List<Long> productIdList) {
        List<OrderProductDto> orderProductList = em.createQuery("select new danta.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
                        " from Cart c" +
                        " join c.cart cl" +
                        " on c.id = cl.cartId" +
                        " join User m" +
                        " on c.id = m.id" +
                        " join Product i" +
                        " on cl.productId = i.id" +
                        " where m.id = :id" +
                        " and cl.productId in :cartProductList", OrderProductDto.class)
                .setParameter("id", id)
                .setParameter("cartProductList", productIdList)
                .getResultList();

        return new OrderSummaryDto(orderProductList);
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
