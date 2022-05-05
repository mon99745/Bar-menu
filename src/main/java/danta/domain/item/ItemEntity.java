package danta.domain.item;

import danta.domain.BaseTimeEntity;
import danta.service.item.NotEnoughStockQuantityException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    private String categoryName;

    @Builder
    public ItemEntity(String imagePath, String name, int price, int stockQuantity, String categoryName) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryName = categoryName;
    }

    // ==== 비즈니스 로직 ====
    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stockQuantity - orderQuantity;

        if(restStockQuantity < 0)
            throw new NotEnoughStockQuantityException();

        this.stockQuantity = restStockQuantity;
    }

    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }
}
