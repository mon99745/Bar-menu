package danta.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractModel is a Querydsl query type for AbstractModel
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAbstractModel extends EntityPathBase<AbstractModel> {

    private static final long serialVersionUID = 1267189389L;

    public static final QAbstractModel abstractModel = new QAbstractModel("abstractModel");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QAbstractModel(String variable) {
        super(AbstractModel.class, forVariable(variable));
    }

    public QAbstractModel(Path<? extends AbstractModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractModel(PathMetadata metadata) {
        super(AbstractModel.class, metadata);
    }

}

