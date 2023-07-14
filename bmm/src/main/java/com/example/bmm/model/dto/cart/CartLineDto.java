package com.example.bmm.model.dto.cart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLineDto {
    private Long productId;
    private String productImage;
    private String productName;
    private int productPrice;
    private int orderCount;

    // 장바구니에서 수량 조절시 제한을 위해 사용(사용자 경험)
    private int stock;

    @QueryProjection
    public CartLineDto(Long productId, String productImage, String productName, int productPrice, int orderCount, int stock) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderCount = orderCount;
        this.stock = stock;
    }
}