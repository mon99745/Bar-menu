package danta.domain.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.dto.cart.CartLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CartCustomRepositoryImpl implements CartCustomRepository{
    @Autowired
    private final EntityManager em;
    private final JPAQueryFactory query;

    public CartCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<CartLineDto> getCartLineListInCartPage(Long userId) {
        List<CartLineDto> cartLineDtoList = em
                .createQuery(
                        "select new danta.model.dto.cart.CartLineDto(cl.cartId, p.id, p.image, p.name, p.price, cl.orderCount, p.stock)" +
                                " from Cart c" +
                                " join c.cart cl" +
                                " on c.id= cl.cartId" +
                                " join Product p" +
                                " on cl.productId = p.id" +
                                " where c.id = : userId", CartLineDto.class)
                .setParameter("userId", userId)
                .getResultList();

        return cartLineDtoList;
    }
}
