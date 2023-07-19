package com.example.bmm.domain.order;

import com.example.bmm.model.dto.order.OrderProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.example.bmm.model.dto.order.OrderSummaryDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

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
            orderProductList = em.createQuery("select new com.example.bmm.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
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
            orderProductList = em.createQuery("select new com.example.bmm.model.dto.order.OrderProductDto(i.id, i.name, i.price, cl.orderCount)" +
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
