package com.example.bmm.domain.cart;


import com.example.bmm.model.dto.cart.CartLineDto;

import java.util.List;

public interface CartCustomRepository{

    /**
     * 장바구니 리스트 조회
     * @param carterId
     * @return
     */
    List<CartLineDto> getCartLineListInCartPage(Long carterId);
}
