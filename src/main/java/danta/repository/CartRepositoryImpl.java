package danta.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.CartDao;
import danta.model.CartLine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartDao {
    private final EntityManager em;
//    private final JPAQueryFactory query;

    public CartRepositoryImpl(EntityManager em) {
        this.em = em;
//        this.query = query;
    }

    @Override
    public List<CartLine> getCartLineListInCartPage(Long userId) {
        List<CartLine> cartLineDtoList = em
                .createQuery(
                        "select new danta.model.CartLine(i.id, cl.productId, cl.orderCount)" +
                                " from Cart c" +
                                " join c.cart cl" +
                                " on c.cart.cartId = cl.cartId" +
                                " join Product i" +
                                " on cl.productId = i. id" +
                                " where c.id = : userId", CartLine.class)
                .setParameter("userId", userId)
                .getResultList();

        return cartLineDtoList;
    }
}
