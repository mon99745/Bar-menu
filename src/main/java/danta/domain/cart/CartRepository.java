package danta.domain.cart;

import danta.model.dto.cart.CartLineDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {
    Cart findCartById(Long userId);

}
