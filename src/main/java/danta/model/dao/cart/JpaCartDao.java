package danta.model.dao.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.model.dto.cart.CartLineDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaCartDao implements CartDao {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaCartDao(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<CartLineDto> getCartLineListInCartPage(Long authId) {
        List<CartLineDto> cartLineDtoList = em
                .createQuery("select new danta.model.dto.cart.CartLineDto(i.itemId, i.imagePath, i.name, i.price, cl.orderCount, i.stockQuantity)" +
                        " from CartEntity c" +
                        " join c.cart cl" +
                        " on c.cartId = cl.cartId" +
                        " join ItemEntity i" +
                        " on cl.itemId = i.itemId" +
                        " where c.authId = : authId", CartLineDto.class)
                .setParameter("authId", authId)
                .getResultList();

        return cartLineDtoList;
    }
}
