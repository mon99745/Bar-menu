package danta.service.product;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductRequest {
    @Length(min = 3)
    private String name;
    @Length(min = 3)
    private String imagePath;
    @Min(0)
    private int price;
    @Min(1)
    private int stockQuantity;
    private String categoryName;
}