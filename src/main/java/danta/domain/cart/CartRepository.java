package danta.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository
        extends JpaRepository<Cart, Long>, CartCustomRepository{

    Cart findCartByCarterId(Long carterId);

}
