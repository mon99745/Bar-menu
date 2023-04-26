package danta.service.product;

import danta.model.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductDetails {
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    public ProductDetails(Product product) {
        this.itemId = product.getId();
        this.imagePath = product.getImage();
        this.name = product.getName();
        this.price = Math.toIntExact(product.getPrice());
        this.stockQuantity = Math.toIntExact(product.getStock());
    }
}