package danta.model.dto.cart;

import danta.controller.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchForm {
    private String name;
    private Sorter sorter;
    private String category;
}
