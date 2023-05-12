package danta.service.product;


import danta.domain.product.Product;
import danta.domain.product.ProductRepository;
import danta.model.dto.product.ProductDetailDto;
import danta.model.dto.product.ProductRequestDto;
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
    public Long saveProduct(ProductRequestDto request) {
        Product newProduct = Product.builder()
                .name(request.getName())
                .image(request.getImage())
                .price (request.getPrice())
                .stock(request.getStock())
                .description(request.getDescription())
                .category(request.getCategory())
                .build();
        newProduct.setId(Long.valueOf(productCountForId()+1));
        //TODO: 상품 상태 활성화/비활성화
//        newProduct.setStatus(true);
        Product savedProduct = productRepository.save(newProduct);

        return savedProduct.getId();
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductDetailDto findProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ProductDetailDto(product);
    }

    public int productCountForId(){
        List<Product> productList = productRepository.findAll();
        return productList.size();
    }
}
