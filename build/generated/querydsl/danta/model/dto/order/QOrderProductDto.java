package danta.model.dto.order;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * danta.model.dto.order.QOrderProductDto is a Querydsl Projection type for OrderProductDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderProductDto extends ConstructorExpression<OrderProductDto> {

    private static final long serialVersionUID = 1874320410L;

    public QOrderProductDto(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> orderCount) {
        super(OrderProductDto.class, new Class<?>[]{long.class, String.class, int.class, int.class}, productId, name, price, orderCount);
    }

}

