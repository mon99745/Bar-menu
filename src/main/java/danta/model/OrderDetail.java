package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 주문 상세 정보 ENTITY
 */
@Schema(description = "주문 상세")
@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail", indexes = {
        @Index(name = "idx_orderDetail_id", columnList = "orderDeatil_id", unique = true),
        @Index(name = "idx_orderDetail_order_id", columnList = "order_id"),
        @Index(name = "idx_orderDetail_product_id", columnList = "product_id"),
        @Index(name = "idx_orderDetail_orderQuantity", columnList = "orderQuantity"),
        @Index(name = "idx_orderDetail_orderPrice", columnList = "orderPrice"),
        @Index(name = "idx_orderDetail_reg_date", columnList = "regDate"),
        @Index(name = "idx_orderDetail_mod_date", columnList = "modDate")})
public class OrderDetail extends AbstractModel {

    /**
     * 주문 상세 ID
     */
    @Id
    @Schema(description = "주문 상세 ID")
    @Column(name = "orderDeatil_id", nullable = false)
    protected String id;

    /**
     * 주문 ID
     */
    @JsonProperty(index = 10)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order orderId;

    /**
     * 상품 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 ID")
    @JoinColumn(name = "product_id")
    protected Product productId;

    /**
     * 주문 수량
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문 수량")
    @Column(length = 100)
    protected int orderQuantity;

    /**
     * 주문 가격
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문 가격")
    @Column(length = 100)
    protected int orderPrice;


}