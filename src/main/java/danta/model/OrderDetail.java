package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 주문 상세 정보 ENTITY
 */
@Schema(description = "주문 상세")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "OrderDetail", indexes = {
        @Index(name = "idx_orderDetail_id", columnList = "orderDetail_id", unique = true),
        @Index(name = "idx_orderDetail_order_id", columnList = "order_id"),
        @Index(name = "idx_orderDetail_product_id", columnList = "product_id"),
        @Index(name = "idx_orderDetail_orderQuantity", columnList = "orderQuantity"),
        @Index(name = "idx_orderDetail_orderPrice", columnList = "orderPrice")})
public class OrderDetail{

    /**
     * 주문 상세 ID
     */
    @Id
    @Schema(description = "주문 상세 ID")
    @Column(name = "orderDetail_id", nullable = false)
    protected Long id;

    /**
     * 주문 ID
     */
    @JsonProperty(index = 20)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order orderId;

    /**
     * 상품 ID
     */
    @JsonProperty(index = 30)
    @Schema(description = "상품 ID")
    @JoinColumn(name = "product_id")
    protected Product productId;

    /**
     * 주문 수량
     */
    @JsonProperty(index = 40)
    @Schema(description = "주문 수량")
    @Column(length = 100)
    protected int orderQuantity;

    /**
     * 주문 가격
     */
    @JsonProperty(index = 50)
    @Schema(description = "주문 가격")
    @Column(length = 100)
    protected int orderPrice;


}