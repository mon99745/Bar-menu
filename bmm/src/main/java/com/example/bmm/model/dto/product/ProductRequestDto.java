package com.example.bmm.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    private String image;
    @Min(0)
    private int price;
    @Min(1)
    private int stock;
    private String description;
    private String category;
}