package danta.model.dao.cart;


import danta.model.dto.cart.CartLineDto;

import java.util.List;

public interface CartDao {
    List<CartLineDto> getCartLineListInCartPage(Long authId);
}
