package danta.service.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLine {
    private Long cart_id;
    private Long itemId;
    private Integer orderCount;

    public CartLine(Long cartId, Long itemId, Integer orderCount) {
        this.cart_id = cartId;
        this.itemId = itemId;
        this.orderCount = orderCount;
    }
}