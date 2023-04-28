package danta.service.product;//package danta.service.item;

import danta.domain.product.ProductRepository;
import danta.model.dto.cart.ProductSearchForm;
import danta.model.dto.product.CatalogSummary;
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

