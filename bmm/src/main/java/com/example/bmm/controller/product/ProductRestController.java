package com.example.bmm.controller.product;

import com.example.bmm.domain.product.Product;
import com.example.bmm.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger 사용을 위해 모든 권한(authh) 을 허용한 상태
 */

@Slf4j
@Api(tags = ProductRestController.TAG)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping(ProductRestController.PATH)
public class ProductRestController {

	@Value("${security.auth}")
	private boolean auth;
	public static final String PATH = "/auth/api/v1/product";
	public static final String TAG = "Product Rest API";
	public final ProductService productService;

	/**
	 * 상품 등록
	 */
	@Operation(summary = "1. 상품 등록")
	@PostMapping("create")
	public Product create(@RequestBody Product createMsg){
		productService.saveProduct(createMsg);
		return createMsg;
	}

	/**
	 * 상품 조회
	 */
	@Operation(summary = "2. 상품 조회")
	@GetMapping("read/{productId}")
	public Product read(@PathVariable Long productId) {
		return productService.findByProductId(productId);
	}

	/**
	 * 상품 수정
	 */
	@Operation(summary = "3. 상품 수정")
	@PostMapping("update/{productId}")
	public Product update(@PathVariable Long productId, @RequestBody Product updateMsg) {
		productService.updateProduct(productId, updateMsg);
		return updateMsg;
	}

	/**
	 * 상품 삭제
	 */
	@Operation(summary = "4. 상품 삭제 ")
	@PostMapping("delete/{productId}")
	public void delete(@PathVariable Long productId, @RequestBody Product product) {
		productService.deleteProduct(productId, product);
	}
}
