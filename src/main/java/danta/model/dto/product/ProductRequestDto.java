package danta.model.dto.product;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductRequestDto {
    @Length(min = 3)
    private String name;
    @Length(min = 3)
    private String imagePath;
    @Min(0)
    private int price;
    @Min(1)
    private int stock;
    private String category;
}