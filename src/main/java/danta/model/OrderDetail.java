package danta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 주문 상세
 */
@Schema(description = "주문 상세")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "idx_orderDetail_id", columnList = "id", unique = true),
        @Index(name = "idx_orderDetail_name", columnList = "name"),
        @Index(name = "idx_orderDetail_price", columnList = "price"),
        @Index(name = "idx_orderDetail_image", columnList = "image"),
        @Index(name = "idx_orderDetail_description", columnList = "description"),
        @Index(name = "idx_orderDetail_status", columnList = "status"),
        @Index(name = "idx_orderDetail_reg_date", columnList = "regDate"),
        @Index(name = "idx_orderDetail_mod_date", columnList = "modDate")})
public class OrderDetail {
    public static final String NAME_SPACE = "OrderDetail";
    public static final String TABLE_NAME = "orderDetail";
    public static final String TABLE_DESC = "주문 상세";

    /**
     * 주문 상세 ID
     */
    @Id
    @Schema(description = "주문 상세 ID")
    @Column(name = "id", nullable = false)
    protected String id;

    /**
     * 주문 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문 ID")
    @Column(length = 100)
    protected Order orderId;

    /**
     * 상품 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "상품 ID")
    @Column(length = 100)
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