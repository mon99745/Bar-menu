package danta.domain.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCartEntity is a Querydsl query type for CartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartEntity extends EntityPathBase<CartEntity> {

    private static final long serialVersionUID = 1237904343L;

    public static final QCartEntity cartEntity = new QCartEntity("cartEntity");

    public final NumberPath<Long> authId = createNumber("authId", Long.class);

    public final MapPath<Long, CartLine, QCartLine> cart = this.<Long, CartLine, QCartLine>createMap("cart", Long.class, CartLine.class, QCartLine.class);

    public final NumberPath<Long> cartId = createNumber("cartId", Long.class);

    public QCartEntity(String variable) {
        super(CartEntity.class, forVariable(variable));
    }

    public QCartEntity(Path<? extends CartEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCartEntity(PathMetadata metadata) {
        super(CartEntity.class, metadata);
    }

}

