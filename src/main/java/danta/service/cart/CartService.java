package danta.service.cart;

import danta.domain.cart.CartEntity;
import danta.domain.cart.CartLine;
import danta.domain.cart.CartRepository;
import danta.domain.item.ItemRepository;

import danta.model.dao.cart.CartDao;
import danta.model.dto.cart.CartLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartDao cartDao;

    public Long createCart(Long authId) {
        return cartRepository.save(new CartEntity(authId))
                .getCartId();
    }


    public void addItemToCart(Long authId, AddToCartRequestForm addToCartRequestForm) {
        CartEntity cartEntity = cartRepository.findByAuthId(authId);

        CartLine newCartLine = new CartLine(cartEntity.getCartId(),
                addToCartRequestForm.getItemId(),
                addToCartRequestForm.getOrderCount());

        int targetStockQuantity = itemRepository.findById(addToCartRequestForm.getItemId())
                .get().getStockQuantity();

        cartEntity.addItemToCart(targetStockQuantity, newCartLine);
    }

    public List<CartLineDto> getCartInCartPage(Long authId) {
        return cartDao.getCartLineListInCartPage(authId);
    }

    public void modifyOrderCount(Long authId, ModifyOrderCountRequestForm modifyOrderCountRequestForm) {
        // 엔티티 조회
        CartEntity cartEntity = cartRepository.findByAuthId(authId);
        int targetStockQuantity = itemRepository.findById(modifyOrderCountRequestForm.getItemId())
                .get()
                .getStockQuantity();

        CartLine newCartLine = new CartLine(cartEntity.getCartId(), modifyOrderCountRequestForm.getItemId(), modifyOrderCountRequestForm.getOrderCount());
        cartEntity.modifyOrderCount(targetStockQuantity, newCartLine);
    }

    //  특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함
    public void removeCartLines(Long authId, List<Long> itemIds) {
        CartEntity cartEntity = cartRepository.findByAuthId(authId);

        itemIds.stream()
                .forEach(itemId -> cartEntity.removeCartLine(itemId));
    }

    public void removeCartLine(Long authId, Long itemId) {
        CartEntity cartEntity = cartRepository.findByAuthId(authId);
        cartEntity.removeCartLine(itemId);
    }
}
