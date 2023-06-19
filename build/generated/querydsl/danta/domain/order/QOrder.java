package danta.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1160935572L;

    public static final QOrder order = new QOrder("order1");

    public final danta.domain.QAbstractModel _super = new danta.domain.QAbstractModel(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> ordererId = createNumber("ordererId", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final ListPath<OrderProduct, QOrderProduct> orderProductList = this.<OrderProduct, QOrderProduct>createList("orderProductList", OrderProduct.class, QOrderProduct.class, PathInits.DIRECT2);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final BooleanPath removed = createBoolean("removed");

    public final DateTimePath<java.time.LocalDateTime> removedAt = createDateTime("removedAt", java.time.LocalDateTime.class);

    public final EnumPath<danta.model.enums.OrderStatus> status = createEnum("status", danta.model.enums.OrderStatus.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

