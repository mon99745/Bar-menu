package danta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주문 상품 정보 ENTITY
 */
@Entity
@Table(name = "order_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends AbstractModel{

    @Id
    private Long orderProductId;
    private int orderCount;
    private int orderProductAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderProduct(Product product, int orderCount) {
        this.product = product;
        this.orderCount = orderCount;
        this.calculateOrderItemAmount();
    }

    private void calculateOrderItemAmount() {
        this.orderProductAmount = (int) (this.product.getPrice() * orderCount);
    }
    public void removeStockQuantity() {
        this.product.removeStockQuantity(orderCount);
    }

    public void cancel() {
        this.product.addStockQuantity(this.orderCount);
    }
}