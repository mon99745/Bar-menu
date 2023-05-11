package danta.domain.order;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.dto.order.OrderProductDto;
import danta.model.dto.order.OrderSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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
    public OrderSummaryDto getOrderSummaryInCart(Authentication authentication, Long id, List<Long> productIdList) {
        List<OrderProductDto> orderProductList;
        if(authentication == null){
            orderProductList = em.createQuery("select new danta.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
                            " from Cart c" +
                            " join c.cart cl" +
                            " on c.id = cl.cartId" +
                            " join Guest g" +
                            " on c.carterId = g.id" +
                            " join Product i" +
                            " on cl.productId = i.id" +
                            " where g.id = :id" +
                            " and cl.productId in :cartProductList", OrderProductDto.class)
                    .setParameter("id", id)
                    .setParameter("cartProductList", productIdList)
                    .getResultList();

        } else{
            orderProductList = em.createQuery("select new danta.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
                            " from Cart c" +
                            " join c.cart cl" +
                            " on c.id = cl.cartId" +
                            " join User u" +
                            " on c.carterId = u.id" +
                            " join Product i" +
                            " on cl.productId = i.id" +
                            " where u.id = :id" +
                            " and cl.productId in :cartProductList", OrderProductDto.class)
                    .setParameter("id", id)
                    .setParameter("cartProductList", productIdList)
                    .getResultList();
        }
        return new OrderSummaryDto(orderProductList);
    }
}
