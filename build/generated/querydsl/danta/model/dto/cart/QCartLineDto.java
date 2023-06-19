package danta.model.dto.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Expression;


/**
 * QCartLineDto is a Querydsl query type for CartLineDto
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCartLineDto extends BeanPath<CartLineDto> {

    private static final long serialVersionUID = 1199977505L;

    public static ConstructorExpression<CartLineDto> create(Expression<Long> productId, Expression<String> productImage, Expression<String> productName, Expression<Integer> productPrice, Expression<Integer> orderCount, Expression<Integer> stock) {
        return Projections.constructor(CartLineDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, int.class}, productId, productImage, productName, productPrice, orderCount, stock);
    }

    public static final QCartLineDto cartLineDto = new QCartLineDto("cartLineDto");

    public final NumberPath<Integer> orderCount = createNumber("orderCount", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productImage = createString("productImage");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QCartLineDto(String variable) {
        super(CartLineDto.class, forVariable(variable));
    }

    public QCartLineDto(Path<? extends CartLineDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCartLineDto(PathMetadata metadata) {
        super(CartLineDto.class, metadata);
    }

}

