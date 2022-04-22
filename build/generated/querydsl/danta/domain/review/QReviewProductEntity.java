package danta.domain.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewProductEntity is a Querydsl query type for ReviewProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewProductEntity extends EntityPathBase<ReviewProductEntity> {

    private static final long serialVersionUID = -1820159810L;

    public static final QReviewProductEntity reviewProductEntity = new QReviewProductEntity("reviewProductEntity");

    public final NumberPath<Integer> fiveCount = createNumber("fiveCount", Integer.class);

    public final NumberPath<Integer> fourCount = createNumber("fourCount", Integer.class);

    public final NumberPath<Integer> oneCount = createNumber("oneCount", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Double> ratingAverage = createNumber("ratingAverage", Double.class);

    public final NumberPath<Long> reviewProductId = createNumber("reviewProductId", Long.class);

    public final NumberPath<Integer> threeCount = createNumber("threeCount", Integer.class);

    public final NumberPath<Integer> totalCount = createNumber("totalCount", Integer.class);

    public final NumberPath<Double> totalRating = createNumber("totalRating", Double.class);

    public final NumberPath<Integer> twoCount = createNumber("twoCount", Integer.class);

    public QReviewProductEntity(String variable) {
        super(ReviewProductEntity.class, forVariable(variable));
    }

    public QReviewProductEntity(Path<? extends ReviewProductEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewProductEntity(PathMetadata metadata) {
        super(ReviewProductEntity.class, metadata);
    }

}

