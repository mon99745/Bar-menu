package danta.domain.guest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGuest is a Querydsl query type for Guest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuest extends EntityPathBase<Guest> {

    private static final long serialVersionUID = 651900456L;

    public static final QGuest guest = new QGuest("guest");

    public final danta.domain.QAbstractModel _super = new danta.domain.QAbstractModel(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final BooleanPath status = createBoolean("status");

    public QGuest(String variable) {
        super(Guest.class, forVariable(variable));
    }

    public QGuest(Path<? extends Guest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuest(PathMetadata metadata) {
        super(Guest.class, metadata);
    }

}

