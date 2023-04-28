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
    public List<CartLineDto> getCartLineListInCartPage(Long id) {
        List<CartLineDto> cartLineDtoList = em
                .createQuery("select new danta.model.dto.cart.CartLineDto(cl.cartId, i.id, i.image, i.name, i.price, cl.orderCount, i.stock)" +
                        " from Cart c" +
                        " join c.cart cl" +
                        " on c.id = cl.cartId" +
                        " join Product i" +
                        " on cl.productId = i.id" +
                        " where c.id = : id", CartLineDto.class)
                .setParameter("id", id)
                .getResultList();

        return cartLineDtoList;
    }
}
