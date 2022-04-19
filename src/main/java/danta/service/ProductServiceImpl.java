package danta.service;

import java.util.List;

import danta.domain.product.Product;
import danta.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //생성자 주입을 받기 위해
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // 01. 상품목록
    @Override
    public List<Product> listProduct() {
        return productRepository.listProduct();
    }
    // 02. 상품상세
    @Override
    public Product detailProduct(int productId) {
        return productRepository.detailProduct(productId);
    }
    // 03. 상품수정
    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }
    // 04. 상품삭제
    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }
    // 05. 상품추가
    @Override
    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }
    // 06. 상품이미지 삭제를 위한 이미지파일 정보
    @Override
    public String fileInfo(int productId) {
        return productRepository.fileInfo(productId);
    }

}