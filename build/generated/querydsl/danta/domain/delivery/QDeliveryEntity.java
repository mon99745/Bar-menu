package danta.domain.delivery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryEntity is a Querydsl query type for DeliveryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryEntity extends EntityPathBase<DeliveryEntity> {

    private static final long serialVersionUID = -171586217L;

    public static final QDeliveryEntity deliveryEntity = new QDeliveryEntity("deliveryEntity");

    public final danta.domain.QBaseTimeEntity _super = new danta.domain.QBaseTimeEntity(this);

    public final SimplePath<org.apache.tomcat.jni.Address> address = createSimple("address", org.apache.tomcat.jni.Address.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> deliveryId = createNumber("deliveryId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    // custom
    public final QDeliveryStatus status = new QDeliveryStatus(forProperty("status"));

    public QDeliveryEntity(String variable) {
        super(DeliveryEntity.class, forVariable(variable));
    }

    public QDeliveryEntity(Path<? extends DeliveryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryEntity(PathMetadata metadata) {
        super(DeliveryEntity.class, metadata);
    }

}

