package danta.service.cart;

import danta.domain.cart.Cart;
import danta.domain.cart.CartLine;
import danta.model.dto.cart.CartLineDto;
import danta.domain.cart.CartRepository;
import danta.domain.product.ProductRepository;
import danta.model.dto.cart.AddToCartRequestFormDto;
import danta.model.dto.cart.ModifyOrderCountRequestFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    /**
     * 장바구니 생성
     * @param cart
     * @return
     */
    public Long createCart(Cart cart) {
        return cartRepository.save(cart).getId();
    }


    /**
     * 장바구니에 상품 추가
     * @param userId
     * @param addToCartRequestForm
     */
    public void addProductToCart(Long userId, AddToCartRequestFormDto addToCartRequestForm) {
        Cart cart = cartRepository.findCartById(userId);

        CartLine newCartLine = new CartLine(cart.getId(),
                addToCartRequestForm.getProductId(),
                addToCartRequestForm.getOrderCount());

        int targetStockQuantity = productRepository.findById(addToCartRequestForm.getProductId())
                .get().getStock();

        cart.addProductToCart(targetStockQuantity, newCartLine);
    }

    /**
     * 장바구니 목록조회
     * @param userId
     * @return
     */
    public List<CartLineDto> getCartInCartPage(Long userId) {
        return cartRepository.getCartLineListInCartPage(userId);
    }

    /**
     * 주문 카운트 수정
     * @param userId
     * @param modifyOrderCountRequestForm
     */

    public void modifyOrderCount(Long userId, ModifyOrderCountRequestFormDto modifyOrderCountRequestForm) {
         //엔티티 조회
        Cart cart = cartRepository.findCartById(userId);
        int targetStock = productRepository.findById(modifyOrderCountRequestForm.getProductId())
                .get()
                .getStock();

        CartLine newCartLine = new CartLine(cart.getId(), modifyOrderCountRequestForm.getProductId(), modifyOrderCountRequestForm.getOrderCount());
        cart.modifyOrderCount(targetStock, newCartLine);
    }

//      특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 id를 기준으로 비워야함

    /**
     * 장바구니 삭제
     * @param userId
     * @param productIds
     */
    public void removeCartLines(Long userId, List<Long> productIds) {
        Cart cart = cartRepository.findCartById(userId);

        productIds.stream()
                .forEach(productId -> cart.removeCartLine(productId));
    }

    public void removeCartLine(Long userId, Long productId) {
        Cart cart = cartRepository.findCartById(userId);
        cart.removeCartLine(productId);
    }
}
