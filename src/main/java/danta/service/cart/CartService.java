package danta.service.cart;

import danta.model.Cart;
import danta.repository.CartRepository;
import danta.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository itemRepository;

    public Long createCart(Cart cart) {
        return cartRepository.save(cart).getId();
    }


    public void addProductToCart(Long authId, AddToCartRequestForm addToCartRequestForm) {
//        Cart cart = cartRepository.findById(Long.valueOf(authId));
//
//        CartLine newCartLine = new CartLine(cart.getId(),
//                addToCartRequestForm.getProductId(),
//                addToCartRequestForm.getOrderCount());
//
//        int targetStock = itemRepository.findById(addToCartRequestForm.getProductId()).get().getStock();
//
//        cart.addProductToCart(targetStock, newCartLine);
    }

//    public List<cartRepository> getCartInCartPage(Long authId) {
//        return cartDao.getCartLineListInCartPage(authId);
//    }

    public void modifyOrderCount(Long authId, ModifyOrderCountRequestForm modifyOrderCountRequestForm) {
        // 엔티티 조회
//        Cart cart = cartRepository.findById(authId);
//        int targetStock = itemRepository.findById(modifyOrderCountRequestForm.getItemId())
//                .get()
//                .getStock();
//
//        CartLine newCartLine = new CartLine(cart.getId(), modifyOrderCountRequestForm.getItemId(), modifyOrderCountRequestForm.getOrderCount());
//        cart.modifyOrderCount(targetStock, newCartLine);
    }

    //  특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함
    public void removeCartLines(Long authId, List<Long> itemIds) {
//        Cart cart = cartRepository.findById(authId);
//
//        itemIds.stream()
//                .forEach(itemId -> cart.removeCartLine(itemId));
    }

    public void removeCartLine(Long authId, Long itemId) {
//        Cart cart = cartRepository.findById(authId);
//        cart.removeCartLine(itemId);
    }
}
