package danta.domain.cart;

import danta.model.dto.cart.CartLineDto;

import java.util.List;

public interface CartCustomRepository{

    /**
     * 장바구니 리스트 조회
     * @param userId
     * @return
     */
    List<CartLineDto> getCartLineListInCartPage(Long userId);
}
