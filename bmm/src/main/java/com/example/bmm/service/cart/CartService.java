package com.example.bmm.service.cart;

import com.example.bmm.domain.cart.Cart;
import com.example.bmm.domain.cart.CartLine;
import com.example.bmm.domain.cart.CartRepository;
import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.model.dto.cart.AddToCartRequestFormDto;
import com.example.bmm.model.dto.cart.CartLineDto;
import com.example.bmm.model.dto.cart.ModifyOrderCountRequestFormDto;
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
     * @param carterId
     * @return
     */
    public Long createCart(Long carterId) {
        return cartRepository.save(new Cart(carterId)).getId();
    }


    /**
     * 장바구니에 상품 추가
     * @param carterId
     * @param addToCartRequestForm
     */
    public void addProductToCart(Long carterId, AddToCartRequestFormDto addToCartRequestForm) {
        Cart cart = cartRepository.findCartByCarterId(carterId);

        CartLine newCartLine = new CartLine(cart.getId(),
                addToCartRequestForm.getProductId(),
                addToCartRequestForm.getOrderCount());

        int targetStockQuantity = productRepository.findById(addToCartRequestForm.getProductId())
                .get().getStock();

        cart.addProductToCart(targetStockQuantity, newCartLine);
    }

    /**
     * 장바구니 목록조회
     * @param carterId
     * @return
     */
    public List<CartLineDto> getCartInCartPage(Long carterId) {
        return cartRepository.getCartLineListInCartPage(carterId);
    }

    /**
     * 주문 카운트 수정
     * @param carterId
     * @param modifyOrderCountRequestForm
     */

    public void modifyOrderCount(Long carterId, ModifyOrderCountRequestFormDto modifyOrderCountRequestForm) {
         //엔티티 조회
        Cart cart = cartRepository.findCartByCarterId(carterId);
        int targetStock = productRepository.findById(modifyOrderCountRequestForm.getProductId())
                .get()
                .getStock();

        CartLine newCartLine = new CartLine(cart.getId(), modifyOrderCountRequestForm.getProductId(), modifyOrderCountRequestForm.getOrderCount());
        cart.modifyOrderCount(targetStock, newCartLine);
    }

//      특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 id를 기준으로 비워야함

    /**
     * 장바구니 삭제
     * @param carterId
     * @param productIds
     */
    public void removeCartLines(Long carterId, List<Long> productIds) {
        Cart cart = cartRepository.findCartByCarterId(carterId);

        productIds.stream()
                .forEach(productId -> cart.removeCartLine(productId));
    }

    public void removeCartLine(Long carterId, Long productId) {
        Cart cart = cartRepository.findCartByCarterId(carterId);
        cart.removeCartLine(productId);
    }
}
