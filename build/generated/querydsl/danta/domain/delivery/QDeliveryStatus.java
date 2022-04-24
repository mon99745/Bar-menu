package danta.domain.delivery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryStatus is a Querydsl query type for DeliveryStatus
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDeliveryStatus extends EnumPath<DeliveryStatus> {

    private static final long serialVersionUID = 234207590L;

    public static final QDeliveryStatus deliveryStatus = new QDeliveryStatus("deliveryStatus");

    public QDeliveryStatus(String variable) {
        super(DeliveryStatus.class, forVariable(variable));
    }

    public QDeliveryStatus(Path<DeliveryStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryStatus(PathMetadata metadata) {
        super(DeliveryStatus.class, metadata);
    }

}

