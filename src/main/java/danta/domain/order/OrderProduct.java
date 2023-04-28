package danta.domain.order;

import danta.domain.product.Product;
import danta.domain.AbstractModel;
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
public class OrderProduct extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private int orderCount;
    private int orderProductAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderProduct(Product product, int orderCount) {
        this.product = product;
        this.orderCount = orderCount;
        this.calculateOrderProductAmount();
    }

    private void calculateOrderProductAmount() {
        this.orderProductAmount = (int) (this.product.getPrice() * orderCount);
    }
    public void removeStockQuantity() {
        this.product.removeStockQuantity(orderCount);
    }

    public void cancel() {
        this.product.addStockQuantity(this.orderCount);
    }
}