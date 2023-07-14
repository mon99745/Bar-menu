package com.example.bmm.domain.product;

import com.example.bmm.model.dto.product.ProductRequestDto;
import com.example.bmm.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	@Test
	public void 아이템_추가() {
		// given
		ProductRequestDto addProductRequest = createAddProductRequest();
		given(productRepository.save(any(Product.class)))
				.willReturn(createProduct(addProductRequest));

		// when
		productService.saveProduct(addProductRequest);

		// then
		verify(productRepository, atLeastOnce()).save(any(Product.class));
	}

	@Test
	public void 아이템_찾기() throws Exception {
		//given
		Long PRODUCT_ID = 1l;
		given(productRepository.findById(any(Long.class)))
				.willReturn(Optional.of(createProduct(createAddProductRequest())));

		//when
		productService.findProduct(PRODUCT_ID);

		//then
		verify(productRepository, atLeastOnce())
				.findById(any(Long.class));
	}

	private ProductRequestDto createAddProductRequest() {
		return ProductRequestDto.builder()
				.image("TEST")
				.name("TEST")
				.price(1000)
				.stock(2)
				.build();
	}

	private Product createProduct(ProductRequestDto request) {
		Product product = Product.builder()
				.price(request.getPrice())
				.name(request.getName())
				.image(request.getImage())
				.stock(request.getStock())
				.build();

		ReflectionTestUtils.setField(product, "id", 1l);
		return product;
	}
}


