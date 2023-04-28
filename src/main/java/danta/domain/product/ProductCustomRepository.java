package danta.domain.product;

import danta.model.dto.cart.ProductSearchForm;
import danta.model.dto.product.CatalogSummary;

import java.util.List;

public interface ProductCustomRepository {
    List<CatalogSummary> searchProduct(ProductSearchForm searchForm);
}
