package danta.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItemEntity is a Querydsl query type for OrderItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItemEntity extends EntityPathBase<OrderItemEntity> {

    private static final long serialVersionUID = 210246794L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItemEntity orderItemEntity = new QOrderItemEntity("orderItemEntity");

    public final danta.domain.QBaseTimeEntity _super = new danta.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final danta.domain.item.QItemEntity item;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> orderCount = createNumber("orderCount", Integer.class);

    public final NumberPath<Integer> orderItemAmount = createNumber("orderItemAmount", Integer.class);

    public final NumberPath<Long> orderItemId = createNumber("orderItemId", Long.class);

    public QOrderItemEntity(String variable) {
        this(OrderItemEntity.class, forVariable(variable), INITS);
    }

    public QOrderItemEntity(Path<? extends OrderItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItemEntity(PathMetadata metadata, PathInits inits) {
        this(OrderItemEntity.class, metadata, inits);
    }

    public QOrderItemEntity(Class<? extends OrderItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new danta.domain.item.QItemEntity(forProperty("item")) : null;
    }

}

