package danta.service.product;


import danta.model.Product;
import danta.repository.ProductRepository;
import danta.service.product.dto.ProductDetail;
import danta.service.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Long saveProduct(ProductRequest request) {
        Product newProduct = Product.builder()
                .name(request.getName())
                .image(request.getImagePath())
                .price((long) request.getPrice())
                .stock(request.getStockQuantity())
                .build();
        Product savedProduct = productRepository.save(newProduct);

        return savedProduct.getId();
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductDetail findItem(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ProductDetail(product);
    }
}
