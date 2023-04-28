package danta.model.dto.order;

import danta.model.enume.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MyOrderDetailDto {
    private LocalDateTime orderDate;
    private Long orderId;
    private List<MyOrderDetailProductDto> orderedProductList;
    private MyOrderDetailReceiverDto receiverInfoDto;
    private OrderStatus orderStatus;
}

