package danta.repository;

import danta.model.Cart;
import danta.model.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {
    Cart findCartById(Long userId);

    List<CartLine> getCartLineListInCartPage(Long userId);

}
