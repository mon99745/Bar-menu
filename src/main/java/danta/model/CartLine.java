package danta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLine {
    private Long cartId;
    private Long productId;
    private Integer orderCount;

    public CartLine(Long cartId, Long productId, Integer orderCount) {
        this.cartId = cartId;
        this.productId = productId;
        this.orderCount = orderCount;
    }
}