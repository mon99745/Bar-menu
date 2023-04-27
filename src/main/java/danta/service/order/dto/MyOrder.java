package danta.service.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MyOrder {
    private Long orderId;
    private LocalDateTime orderDate;
    private String representativeImagePath;
    private String representativeItemName;
    private int totalAmount;
    private String orderStatus;

    @Builder
    public MyOrder(Long orderId, LocalDateTime orderDate,
                   String representativeImagePath, String representativeItemName,
                   int totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.representativeImagePath = representativeImagePath;
        this.representativeItemName = representativeItemName;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
