package danta.service.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyOrderDetailProduct {
    private Long itemId;
    private String itemImagePath;
    private String itemName;
    private int itemPrice;
}

