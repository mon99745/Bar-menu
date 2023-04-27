package danta.service.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSummary {
    private List<OrderIProduct> orderItemList;
    private int totalAmount;

    public OrderSummary(List<OrderIProduct> orderItemList) {
        this.orderItemList = orderItemList;
        this.totalAmount = orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getTotalAmount())
                .sum();
    }
}
