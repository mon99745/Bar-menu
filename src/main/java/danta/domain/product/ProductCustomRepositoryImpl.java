package danta.domain.product;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import danta.controller.Sorter;
import danta.model.dto.cart.ProductSearchForm;
import danta.model.dto.product.CatalogSummary;
import danta.model.dto.product.QCatalogSummary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static danta.domain.product.QProduct.product;

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository{
    private JPAQueryFactory query;

    public ProductCustomRepositoryImpl(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }

    public List<CatalogSummary> searchProduct(ProductSearchForm searchForm)  {
        return query
                .select(new QCatalogSummary(product.id, product.image, product.name, product.price))
                .from(product)
                .where(nameLike(searchForm.getName()), categoryEq(searchForm.getCategoryName()))
                .orderBy(sorter(searchForm.getSorter()))
                .fetch();
    }



    private Predicate nameLike(String name) {
        if (name != null && name.length() > 0)
            return product.name.like("%" + name + "%");
        return null;
    }

    private Predicate categoryEq(String categoryName) {
        if (categoryName != null)
            return product.category.eq(categoryName);
        return null;
    }

    private OrderSpecifier sorter(Sorter sorter) {
        if (sorter == null)
            return product.regDate.desc();

        if (sorter == Sorter.PRICE)
            return product.price.desc();

        if (sorter == Sorter.LATEST)
            return product.regDate.desc();

        return product.regDate.desc();
    }
}
