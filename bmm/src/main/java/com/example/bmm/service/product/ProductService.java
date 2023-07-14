package com.example.bmm.service.product;


import com.example.bmm.domain.product.Product;
import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.model.dto.product.ProductDetailDto;
import com.example.bmm.model.dto.product.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * 상품 등록 (파라미터)
     * @param request
     * @return
     */
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

    /**
     * 상품 등록 (JSON)
     * @param product
     * @return
     */
    @Transactional
    public Long saveProduct(Product product) {

        //TODO: 상품 상태 활성화/비활성화
//        newProduct.setStatus(true);
        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }

    /**
     * 상품 전체 조회
     * @return
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * 상품 조회
     * @param productId
     * @return
     */
    public ProductDetailDto findProduct(Long productId) {
        Product product = validateExistProduct(productRepository.findById(productId));
        return new ProductDetailDto(product);
    }

    /**
     * 상품 조회
     * @param productId
     * @return
     */
    public Product findByProductId(Long productId) {
        Product product = validateExistProduct(productRepository.findById(productId));
        return product;
    }

    /**
     * 상품 수정
     */
    public Product updateProduct(Long productId, Product product){
        Product tempProduct = productRepository.findById(productId).orElseThrow(()
                -> new IllegalArgumentException("해당 상품이 없습니다. id=" + product.getId()));
        // 수정 가능한 정보
        tempProduct.setName(product.getName());
        tempProduct.setStatus(product.getStatus());
        tempProduct.setImage(product.getImage());
        tempProduct.setPrice(product.getPrice());

        return tempProduct;
    }

    /**
     * 상품 삭제
     */
    public void deleteProduct(Long productId, Product product){
        product.setId(productId);
        productRepository.delete(product);
    }

    /**
     * ID 에 따른 상품 카운트
     * @return
     */
    public int productCountForId(){
        List<Product> productList = productRepository.findAll();
        return productList.size();
    }

    /**
     * 상품 예외검증
     */
    private Product validateExistProduct(Optional<Product> product) {
        if(!product.isPresent())
            throw new IllegalStateException("존재하지 않는 상품입니다.");
        return product.get();
    }
}
