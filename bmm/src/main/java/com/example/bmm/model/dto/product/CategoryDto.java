package com.example.bmm.model.dto.product;

import com.example.bmm.model.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class CategoryDto implements Serializable {
    private Long categoryId;
    private Category category;
//    private Long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(Long categoryId, Category category) {
        this.categoryId = categoryId;
        this.category = category;
//        this.parentId = parentId;
    }
}

