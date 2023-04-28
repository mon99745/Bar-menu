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
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    public ProductDetailDto(Product product) {
        this.ProductId = product.getId();
        this.imagePath = product.getImage();
        this.name = product.getName();
        this.price = Math.toIntExact(product.getPrice());
        this.stockQuantity = Math.toIntExact(product.getStock());
    }
}