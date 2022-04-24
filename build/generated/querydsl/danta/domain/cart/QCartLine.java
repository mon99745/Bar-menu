package danta.domain.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCartLine is a Querydsl query type for CartLine
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCartLine extends BeanPath<CartLine> {

    private static final long serialVersionUID = 1655121128L;

    public static final QCartLine cartLine = new QCartLine("cartLine");

    public final NumberPath<Long> cartId = createNumber("cartId", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final NumberPath<Integer> orderCount = createNumber("orderCount", Integer.class);

    public QCartLine(String variable) {
        super(CartLine.class, forVariable(variable));
    }

    public QCartLine(Path<? extends CartLine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCartLine(PathMetadata metadata) {
        super(CartLine.class, metadata);
    }

}

