package com.example.bmm.service.product;

import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.model.dto.cart.ProductSearchForm;
import com.example.bmm.model.dto.product.CatalogSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {
    private final ProductRepository productRepository;


    public List<CatalogSummary> getCatalog(ProductSearchForm searchForm) {
        return productRepository.searchProduct(searchForm);
    }
}

