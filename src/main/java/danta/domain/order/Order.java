package danta.domain.order;


import com.fasterxml.jackson.annotation.JsonProperty;
import danta.domain.AbstractModel;
import danta.domain.user.User;
import danta.model.enume.OrderStatus;
import danta.model.enume.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보 ENTITY
 */
@Schema(description = "주문")
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "OrderInfo",indexes = {
        @Index(name = "idx_order_id", columnList = "order_id", unique = true),
        @Index(name = "idx_order_orderer", columnList = "user_id"),
        @Index(name = "idx_order_price", columnList = "price"),
        @Index(name = "idx_order_status", columnList = "status"),
        @Index(name = "idx_order_reg_date", columnList = "regDate"),
        @Index(name = "idx_order_mod_date", columnList = "modDate")})

public class Order extends AbstractModel {

    /**
     * 주문 ID
     */
    @Id
    @Schema(description = "주문 ID")
    @Column(name = "order_id", nullable = false)
    protected Long orderId;

    /**
     * 주문자 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문자 ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User orderer;

    /**
     * 주문 상태
     */
    @JsonProperty(index = 20)
    @Schema(description = "주문 상태")
    @Column(length = 100)
    protected OrderStatus status;

    /**
     * 주문 금액
     */
    @JsonProperty(index = 30)
    @Schema(description = "주문 금액")
    @Column(length = 100)
    protected Long price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private boolean removed;
    private LocalDateTime removedAt;
    private int totalAmount;

    @Builder
    public Order(User orderer, List<OrderProduct> orderProductList) {
        this.orderer = orderer;
        this.setOrderProductList(orderProductList);
        this.status = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderProductList(List<OrderProduct> orderProductList) {
        orderProductList.stream()
                .forEach(orderProduct -> this.orderProductList.add(orderProduct));
        this.calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        this.totalAmount = this.orderProductList.stream()
                .mapToInt(orderProduct -> orderProduct.getOrderProductAmount())
                .sum();
    }

    // ==== 비즈니스 로직 ====
    public void cancel() {
        this.orderProductList.stream()
                .forEach(orderProduct -> orderProduct.cancel());
    }

    public void deleteOrder(Long ordererId) {
        if (ordererId != this.orderer.getId())
            throw new IllegalStateException("주문자와 삭제 요청자가 일치하지 않습니다.");

        this.removed = true;
        this.removedAt = LocalDateTime.now();
    }
}