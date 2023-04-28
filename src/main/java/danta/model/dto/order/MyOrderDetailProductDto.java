package danta.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyOrderDetailProductDto {
    private Long productId;
    private String productImagePath;
    private String productName;
    private int productPrice;
}

