package danta.model.dto.product;

import danta.domain.product.Product;
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

    public ProductDetailDto(Product product) {
        this.ProductId = product.getId();
        this.image = product.getImage();
        this.name = product.getName();
        this.price = Math.toIntExact(product.getPrice());
        this.stock = Math.toIntExact(product.getStock());
    }
}