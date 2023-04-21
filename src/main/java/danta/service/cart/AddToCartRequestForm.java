package danta.service.cart;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddToCartRequestForm {
    private String ProductId;

    @Min(1)
    private Integer orderCount;
}

