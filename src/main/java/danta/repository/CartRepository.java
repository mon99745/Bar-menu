package danta.repository;

import danta.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository
        extends JpaRepository<Cart, Long> {
}
