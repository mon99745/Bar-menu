package danta.service.order.dto;

import com.danta.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MyOrderDetail {
    private LocalDateTime orderDate;
    private Long orderId;
    private List<MyOrderDetailProduct> orderedItemList;
    private MyOrderDetailReceiver receiverInfoDto;
    private OrderStatus orderStatus;
}

