package danta.service.cart.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ModifyOrderCountRequestForm {
    private Long productId;
    @Min(1)
    private int orderCount;
}
