package com.example.bmm.model.dto.product;

import com.example.bmm.domain.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductDetailDto {
    private Long ProductId;
    private String image;
    private String name;
    private int price;
    private int stock;
    private String description;

    public ProductDetailDto(Product product) {
        this.ProductId = product.getId();
        this.image = product.getImage();
        this.name = product.getName();
        this.price = Math.toIntExact(product.getPrice());
        this.stock = Math.toIntExact(product.getStock());
        this.description = product.getDescription();
    }
}