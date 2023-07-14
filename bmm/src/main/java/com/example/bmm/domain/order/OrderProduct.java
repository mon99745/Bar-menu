package com.example.bmm.domain.order;

import com.example.bmm.domain.AbstractModel;
import com.example.bmm.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 주문 상품 ENTITY
 */
@Schema(description = "주문 상품")
@Data
@SuperBuilder
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