package danta.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import danta.common.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 */
@Schema(description = "주문")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Table(indexes = {
        @Index(name = "idx_order_id", columnList = "id", unique = true),
        @Index(name = "idx_order_name", columnList = "name"),
        @Index(name = "idx_order_price", columnList = "price"),
        @Index(name = "idx_order_image", columnList = "image"),
        @Index(name = "idx_order_description", columnList = "description"),
        @Index(name = "idx_order_status", columnList = "status"),
        @Index(name = "idx_order_reg_date", columnList = "regDate"),
        @Index(name = "idx_order_mod_date", columnList = "modDate")})
@org.hibernate.annotations.Table(appliesTo = Order.TABLE_NAME, comment = Order.TABLE_DESC)
public class Order {
    public static final String NAME_SPACE = "Order";
    public static final String TABLE_NAME = "order";
    public static final String TABLE_DESC = "주문";

    private boolean removed;
    private LocalDateTime removedAt;
    private int totalAmount;

    /**
     * 주문 ID
     */
    @Id
    @Schema(description = "주문 ID")
    @Column(name = "id", nullable = false)
    protected String orderId;

    /**
     * 주문자 이름
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문자 이름")
    @Column(length = 100)
    protected User orderer;

    /**
     * 주문 상태
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문 상태")
    @Column(length = 100)
    protected String status;

    /**
     * 주문 금액
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문 금액")
    @Column(length = 100)
    protected Long price;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderProduct> orderProductList = new ArrayList<>();


    private void setOrderItemList(List<OrderProduct> orderItemEntityList) {
        orderItemEntityList.stream()
                .forEach(orderItemEntity -> this.orderProductList.add(orderItemEntity));
        this.calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        this.totalAmount = this.orderProductList.stream()
                .mapToInt(orderItem -> orderItem.getOrderProductAmount())
                .sum();
    }

    // ==== 비즈니스 로직 ====
    public void cancel() {
        this.orderProductList.stream()
                .forEach(orderItem -> orderItem.cancel());
    }

    public void deleteOrder(Long ordererId) {
//        if (ordererId != this.orderer.getId())
//            throw new IllegalStateException("주문자와 삭제 요청자가 일치하지 않습니다.");

        this.removed = true;
        this.removedAt = LocalDateTime.now();
    }
}