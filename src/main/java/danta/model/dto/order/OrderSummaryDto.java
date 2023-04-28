package danta.model.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSummaryDto {
    private List<OrderProductDto> orderItemList;
    private int totalAmount;

    public OrderSummaryDto(List<OrderProductDto> orderItemList) {
        this.orderItemList = orderItemList;
        this.totalAmount = orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getTotalAmount())
                .sum();
    }
}
