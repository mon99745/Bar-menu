package danta.model.dto.product;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * danta.model.dto.product.QCatalogSummary is a Querydsl Projection type for CatalogSummary
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCatalogSummary extends ConstructorExpression<CatalogSummary> {

    private static final long serialVersionUID = -1586581168L;

    public QCatalogSummary(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<String> image, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price) {
        super(CatalogSummary.class, new Class<?>[]{long.class, String.class, String.class, int.class}, productId, image, name, price);
    }

}

