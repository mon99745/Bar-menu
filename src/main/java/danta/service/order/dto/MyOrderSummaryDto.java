package danta.service.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MyOrderSummaryDto {
    private List<MyOrder> myOrderList;
    private int total;
}
