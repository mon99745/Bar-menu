package danta.domain.order;

import danta.domain.product.Product;
import danta.domain.AbstractModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주문 상품 ENTITY
 */
@Schema(description = "주문 상품")
@Data
@Entity
@Table(name = "order_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends AbstractModel {


    /**
     * 주문 상품 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    /**
     * 주문 상품 카운트
     */
    private int orderCount;

    /**
     * 주문 상품 수량
     */
    private int orderProductAmount;

    /**
     * 주문 상품
     */
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