package danta.model;

import java.util.List;

public interface CartDao {
    List<CartLine> getCartLineListInCartPage(Long userId);
}

