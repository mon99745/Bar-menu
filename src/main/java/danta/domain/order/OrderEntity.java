package danta.domain.order;

import danta.domain.BaseTimeEntity;
import danta.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private int totalAmount;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private boolean removed;
    private LocalDateTime removedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User orderer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    @Builder
    public OrderEntity(User orderer, List<OrderItemEntity> orderItemEntityList) {
        this.orderer = orderer;
        this.setOrderItemList(orderItemEntityList);
        this.status = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderItemList(List<OrderItemEntity> orderItemEntityList) {
        orderItemEntityList.stream()
                .forEach(orderItemEntity -> this.orderItemList.add(orderItemEntity));
        this.calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        this.totalAmount = this.orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getOrderItemAmount())
                .sum();
    }

    // ==== 비즈니스 로직 ====
    public void cancel() {
        this.orderItemList.stream()
                .forEach(orderItem -> orderItem.cancel());
        this.status = OrderStatus.CANCEL_STATUS;
    }

    public void deleteOrder(Long ordererId) {
        if (ordererId != this.orderer.getAuthId())
            throw new IllegalStateException("주문자와 삭제 요청자가 일치하지 않습니다.");

        this.removed = true;
        this.removedAt = LocalDateTime.now();
    }
}
