package danta.domain.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 어떤 아이템을, 얼만큼 담았는지를 표현할 수 있는 오브젝트
 */
@Embeddable
@NoArgsConstructor
@Data
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
