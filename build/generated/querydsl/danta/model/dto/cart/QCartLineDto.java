package danta.model.dto.cart;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * danta.model.dto.cart.QCartLineDto is a Querydsl Projection type for CartLineDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCartLineDto extends ConstructorExpression<CartLineDto> {

    private static final long serialVersionUID = 1199977505L;

    public QCartLineDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> itemImagePath, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> itemPrice, com.querydsl.core.types.Expression<Integer> orderCount, com.querydsl.core.types.Expression<Integer> stockQuantity) {
        super(CartLineDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, int.class}, itemId, itemImagePath, itemName, itemPrice, orderCount, stockQuantity);
    }

}

