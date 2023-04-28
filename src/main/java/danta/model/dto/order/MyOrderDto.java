package danta.model.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MyOrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String representativeImagePath;
    private String representativeProductName;
    private int totalAmount;
    private String orderStatus;

    @Builder
    public MyOrderDto(Long orderId, LocalDateTime orderDate,
                      String representativeImagePath, String representativeProductName,
                      int totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.representativeImagePath = representativeImagePath;
        this.representativeProductName = representativeProductName;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}
