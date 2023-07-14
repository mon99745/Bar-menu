package com.example.bmm.model.dto.product;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatalogSummary {
    private Long productId;
    private String image;
    private String name;
    private int price;

    @QueryProjection
    public CatalogSummary(Long productId, String image, String name, int price) {
        this.productId = productId;
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
