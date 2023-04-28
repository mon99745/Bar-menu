package danta.model.dto.order;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductDto {
    private Long productId;
    private String name;
    private int price;
    private int orderCount;
    private int totalAmount;

    @QueryProjection
    public OrderProductDto(Long productId, String name, int price, int orderCount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.orderCount = orderCount;
        this.totalAmount = price * orderCount;
    }
}
