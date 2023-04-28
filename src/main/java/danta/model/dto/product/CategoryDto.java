package danta.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class CategoryDto implements Serializable {
    private Long categoryId;
    private String categoryName;
//    private Long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
//        this.parentId = parentId;
    }
}

