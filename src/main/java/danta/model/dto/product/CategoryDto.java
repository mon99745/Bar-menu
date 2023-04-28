package danta.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class CategoryDto implements Serializable {
    private Long categoryId;
    private String category;
//    private Long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(Long categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
//        this.parentId = parentId;
    }
}

