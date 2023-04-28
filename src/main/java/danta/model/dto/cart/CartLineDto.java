package danta.model.dto.cart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLineDto {
    private Long cartId;

    private Long productId;
    private String ProductImage;
    private String productName;
    private int productPrice;
    private int orderCount;

    // 장바구니에서 수량 조절시 제한을 위해 사용(사용자 경험)
    private int stock;

    @QueryProjection
    public CartLineDto(Long cartId, Long productId, int productPrice) {
        this.cartId = cartId;
        this.productId = productId;
        this.ProductImage = ProductImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderCount = orderCount;
        this.stock = stock;
    }
}