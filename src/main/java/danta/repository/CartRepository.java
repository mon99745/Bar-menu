package danta.repository;

import danta.model.cart.Cart;
import danta.service.cart.dto.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {
    Cart findCartById(Long userId);

    List<CartLine> getCartLineListInCartPage(Long userId);

}
