package danta.model.dto.order;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * danta.model.dto.order.QOrderItemDto is a Querydsl Projection type for OrderItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderItemDto extends ConstructorExpression<OrderItemDto> {

    private static final long serialVersionUID = -1377775518L;

    public QOrderItemDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> orderCount) {
        super(OrderItemDto.class, new Class<?>[]{long.class, String.class, int.class, int.class}, itemId, name, price, orderCount);
    }

}

