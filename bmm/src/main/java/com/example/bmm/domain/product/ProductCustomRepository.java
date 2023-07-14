package com.example.bmm.domain.product;


import com.example.bmm.model.dto.cart.ProductSearchForm;
import com.example.bmm.model.dto.product.CatalogSummary;

import java.util.List;

public interface ProductCustomRepository {
    List<CatalogSummary> searchProduct(ProductSearchForm searchForm);
}
