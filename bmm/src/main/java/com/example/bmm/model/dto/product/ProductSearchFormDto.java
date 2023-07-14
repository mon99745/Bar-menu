package com.example.bmm.model.dto.product;

import com.example.bmm.model.enums.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchFormDto {
    private String name;
    private Sorter sorter;
    private String category;

}