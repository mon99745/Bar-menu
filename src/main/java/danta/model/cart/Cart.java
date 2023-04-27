package danta.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import danta.exception.NotEnoughStockQuantityException;
import danta.model.AbstractModel;
import danta.model.user.User;
import danta.service.cart.dto.CartLine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 장바구니 정보 ENTITY
 */
@Schema(description = "장바구니")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CartInfo", indexes = {
        @Index(name = "idx_cart_id", columnList = "cart_id", unique = true),
        @Index(name = "idx_cart_carterId", columnList = "user_id"),
        @Index(name = "idx_cart_quantity", columnList = "quantity"),
        @Index(name = "idx_cart_reg_date", columnList = "regDate"),
        @Index(name = "idx_cart_mod_date", columnList = "modDate")})

public class Cart extends AbstractModel {

    /**
     * 장바구니 ID
     */
    @Id
    @Schema(description = "장바구니 ID")
    @Column(name = "cart_id", nullable = false)
    protected Long id;

    /**
     * 장바구니 사용자 ID
     */
    @JsonProperty
    @Schema(description = "장바구니 사용자 ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User carterId;


    /**
     * 장바구니에 담긴 수량
     */
    @JsonProperty(index = 10)
    @Schema(description = "수량")
    @Column(length = 100)
    protected int quantity;

    // Map을 이용하므로써 @ElementCollection의 단점을 보완
    // itemId를 키로 사용
    // 1. 수정시, 모든 로우를 삭제 후, 수정된 로우를 추가하는 문제
    // 2. 삭제시, 모든 로우를 삭제 후, 삭제 대상을 제외한 모든 로우를 다시 입력하는 문제
    @ElementCollection
    @CollectionTable(name = "cart_line")
    @MapKeyColumn(name = "map_key")
    private Map<Long, CartLine> cart = new HashMap<>();

    public void addProductToCart(int targetStock, CartLine cartLine) {
        verifyEnoughStockQuantity(targetStock, cartLine.getOrderCount());

        Long mapKey = cartLine.getProductId();

        // 기존 아이템이 존재한다면 수량을 더함
        if (cart.containsKey(mapKey)) {
            CartLine existCartLine = cart.get(cartLine.getProductId());
            int newOrderCount = existCartLine.getOrderCount() + cartLine.getOrderCount();
            cart.replace(mapKey, new CartLine(id, cartLine.getProductId(), newOrderCount));
        }
        else {
            cart.put(mapKey, cartLine);
        }
    }

    public void modifyOrderCount(int targetStockQuantity, CartLine newCartLine) {
        verifyEnoughStockQuantity(targetStockQuantity, newCartLine.getOrderCount());

        this.cart.replace(newCartLine.getProductId(), newCartLine);
    }

    public void removeCartLine(Long cartItemId) {
        this.cart.remove(cartItemId);
    }

    private void verifyEnoughStockQuantity(int targetStockQuantity, int orderCount) {
        if (orderCount > targetStockQuantity) {
            throw new NotEnoughStockQuantityException("재고량이 충분하지 않습니다.");
        }
    }
}