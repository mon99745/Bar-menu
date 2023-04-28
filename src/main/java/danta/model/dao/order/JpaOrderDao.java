package danta.model.dao.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.dto.order.OrderProductDto;
import danta.model.dto.order.OrderSummaryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaOrderDao implements OrderDao {
    private EntityManager em;
    private JPAQueryFactory query;

    public JpaOrderDao(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public OrderSummaryDto getOrderSummaryInCart(Long id, List<Long> productIdList) {
        List<OrderProductDto> orderItemList = em.createQuery("select new danta.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
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

        return new OrderSummaryDto(orderItemList);
    }
}
