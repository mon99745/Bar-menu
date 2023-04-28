package danta.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyOrderDetailProductDto {
    private Long itemId;
    private String itemImagePath;
    private String itemName;
    private int itemPrice;
}

