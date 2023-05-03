package danta.model.dto.cart;

import danta.model.enums.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchForm {
    private String name;
    private Sorter sorter;
    private String category;
}
