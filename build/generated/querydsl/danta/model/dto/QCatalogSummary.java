package danta.model.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * danta.model.dto.QCatalogSummary is a Querydsl Projection type for CatalogSummary
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCatalogSummary extends ConstructorExpression<CatalogSummary> {

    private static final long serialVersionUID = 1093273201L;

    public QCatalogSummary(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> imagePath, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Double> reviewRating, com.querydsl.core.types.Expression<Integer> reviewCount) {
        super(CatalogSummary.class, new Class<?>[]{long.class, String.class, String.class, int.class, double.class, int.class}, itemId, imagePath, name, price, reviewRating, reviewCount);
    }

}

