//package danta.domain.cart;
//
//import danta.model.dto.cart.CartLineDto;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Repository
//public class CartRepositoryImpl{
//    private final EntityManager em;
////    private final JPAQueryFactory query;
//
//    public CartRepositoryImpl(EntityManager em) {
//        this.em = em;
////        this.query = query;
//    }
//
//    public List<CartLineDto> getCartLineListInCartPage(Long userId) {
//        List<CartLineDto> cartLineDtoList = em
//                .createQuery(
//                        "select new danta.model.CartLine(i.id, cl.productId, cl.orderCount)" +
//                                " from Cart c" +
//                                " join c.cart cl" +
//                                " on c.cart.cartId = cl.cartId" +
//                                " join Product i" +
//                                " on cl.productId = i. id" +
//                                " where c.id = : userId", CartLineDto.class)
//                .setParameter("userId", userId)
//                .getResultList();
//
//        return cartLineDtoList;
//    }
//}
