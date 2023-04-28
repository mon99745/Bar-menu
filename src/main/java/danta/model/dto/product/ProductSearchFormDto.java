package danta.model.dto.product;

import danta.controller.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchFormDto {
    private String name;
    private Sorter sorter;
    private String categoryName;

}