package danta.service;

import danta.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    // 01. 상품목록
    public List<Product> listProduct();
    // 02. 상품상세
    public Product detailProduct(int productId);
    // 03. 상품수정
    public void updateProduct(Product vo);
    // 04. 상품삭제
    public void deleteProduct(int productId);
    // 05. 상품추가
    public void insertProduct(Product vo);
    // 06. 상품이미지 삭제를 위한 이미지파일 정보
    public String fileInfo(int productId);

}