package com.example.bmm.domain.order;


import com.example.bmc.exception.BmcException;
import com.example.bmc.exception.BmmError;
import com.example.bmm.domain.AbstractModel;
import com.example.bmm.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보 ENTITY
 */
@Schema(description = "주문")
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Embeddable
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "OrderInfo",indexes = {
        @Index(name = "idx_order_id", columnList = "order_id", unique = true),
        @Index(name = "idx_order_orderer_id", columnList = "orderer_id"),
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long orderId;

    /**
     * 주문자 ID
     */
    @JsonProperty(index = 10)
    @Schema(description = "주문자 ID")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    @Column(name = "orderer_id")
    protected Long ordererId;

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
    public Order(Long ordererId, List<OrderProduct> orderProductList) {
        this.ordererId = ordererId;
        this.setOrderProductList(orderProductList);
        this.status = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderProductList(List<OrderProduct> orderProductList) {
        orderProductList.stream()
                .forEach(orderProduct -> this.orderProductList.add(orderProduct));
        this.calculateTotalAmount();
    }

    /**
     * 비즈니스 로직
     * 주문 취소
     */
    public void cancel() {
        this.orderProductList.stream()
                .forEach(orderProduct -> orderProduct.cancel());
    }

    /**
     * 조회 로직
     * 전체 주문 가격 조회
     */
    private void calculateTotalAmount() {
        this.totalAmount = this.orderProductList.stream()
                .mapToInt(orderProduct -> orderProduct.getOrderProductAmount())
                .sum();
    }

    public void deleteOrder(Long ordererId) {
        if (ordererId != this.ordererId) {
            throw new BmcException(BmmError.BMM_ORDER_AND_REQUESTER_NOT_MATCH, null);
        }

        this.removed = true;
        this.removedAt = LocalDateTime.now();
    }
}