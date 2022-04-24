package danta.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 345344407L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final danta.domain.QBaseTimeEntity _super = new danta.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final danta.domain.delivery.QDeliveryEntity deliveryInformation;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final danta.domain.user.QUser orderer;

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final ListPath<OrderItemEntity, QOrderItemEntity> orderItemList = this.<OrderItemEntity, QOrderItemEntity>createList("orderItemList", OrderItemEntity.class, QOrderItemEntity.class, PathInits.DIRECT2);

    public final BooleanPath removed = createBoolean("removed");

    public final DateTimePath<java.time.LocalDateTime> removedAt = createDateTime("removedAt", java.time.LocalDateTime.class);

    public final EnumPath<OrderStatus> status = createEnum("status", OrderStatus.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QOrderEntity(String variable) {
        this(OrderEntity.class, forVariable(variable), INITS);
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderEntity(PathMetadata metadata, PathInits inits) {
        this(OrderEntity.class, metadata, inits);
    }

    public QOrderEntity(Class<? extends OrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.deliveryInformation = inits.isInitialized("deliveryInformation") ? new danta.domain.delivery.QDeliveryEntity(forProperty("deliveryInformation")) : null;
        this.orderer = inits.isInitialized("orderer") ? new danta.domain.user.QUser(forProperty("orderer")) : null;
    }

}

