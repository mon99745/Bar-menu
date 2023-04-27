package danta.service.cart.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddToCartRequestForm {
    private Long productId;

    @Min(1)
    private Integer orderCount;
}

