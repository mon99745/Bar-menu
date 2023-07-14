package com.example.bmm.domain.cart;


import com.example.bmm.domain.product.Product;
import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.model.dto.cart.AddToCartRequestFormDto;
import com.example.bmm.model.dto.cart.ModifyOrderCountRequestFormDto;
import com.example.bmm.service.cart.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = {
		CartService.class,
})
class CartServiceTest {
	@Autowired
	private CartService cartService;

	@MockBean
	private CartRepository cartRepository;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private CartCustomRepositoryImpl cartDao;

	private final Long TEST_MEMBER_ID = 1l;
	private final Long TEST_ITEM_ID = 2l;

	@BeforeEach
	public void init() {
		Cart cart = new Cart(1l);
		given(cartRepository.save(cart))
				.willReturn(cart);
		given(cartRepository.findCartByCarterId(TEST_MEMBER_ID))
				.willReturn(cart);
		given(productRepository.findById(anyLong()))
				.willReturn(Optional.of(createProduct()));
	}

	private Product createProduct() {
		return Product.builder()
				.price(1000)
				.name("TEST")
				.image("TEST")
				.category("탕")
				.stock(10)
				.build();
	}

	@Test
	public void addProductToCart() throws Exception {
		//given
		int orderCount = 1;
		AddToCartRequestFormDto request = createAddToCartRequestFormDto(orderCount);

		//when
		cartService.addProductToCart(TEST_MEMBER_ID, request);

		//then
		Map<Long, CartLine> cart = cartRepository.findCartByCarterId(TEST_MEMBER_ID).getCart();
		assertEquals("장바구니에 추가된 주문 수량이 일치해야함.", cart.get(TEST_ITEM_ID).getOrderCount(), orderCount);
	}

	@Test
	public void modifyOrderCount() throws Exception {
		//given
		// 장바구니 담기
		int orderCount = 1;
		AddToCartRequestFormDto request = createAddToCartRequestFormDto(orderCount);
		cartService.addProductToCart(TEST_MEMBER_ID, request);

		int newOrderCount = 2;
		ModifyOrderCountRequestFormDto modifyRequest = createModifyOrderCountRequestFormDto(newOrderCount);

		//when
		cartService.modifyOrderCount(TEST_MEMBER_ID, modifyRequest);

		//then
		Map<Long, CartLine> cart = cartRepository.findCartByCarterId(TEST_MEMBER_ID).getCart();
		assertEquals("장바구니 주문 수량이 변경한 주문수량과 일치해야함.", cart.get(TEST_ITEM_ID).getOrderCount(), newOrderCount);
	}

	private ModifyOrderCountRequestFormDto createModifyOrderCountRequestFormDto(int newOrderCount) {
		ModifyOrderCountRequestFormDto modifyRequest = new ModifyOrderCountRequestFormDto();
		modifyRequest.setProductId(TEST_ITEM_ID);
		modifyRequest.setOrderCount(newOrderCount);

		return modifyRequest;
	}

	private AddToCartRequestFormDto createAddToCartRequestFormDto(int orderCount) {
		AddToCartRequestFormDto request = new AddToCartRequestFormDto();
		request.setProductId(TEST_ITEM_ID);
		request.setOrderCount(orderCount);
		return request;
	}

	@Test
	public void removeCartLine() throws Exception {
		//given
		// 장바구니 담기
		int orderCount = 1;
		AddToCartRequestFormDto request = createAddToCartRequestFormDto(orderCount);
		cartService.addProductToCart(TEST_MEMBER_ID, request);

		//when
		cartService.removeCartLine(TEST_MEMBER_ID, TEST_ITEM_ID);

		//then
		Map<Long, CartLine> cart = cartRepository.findCartByCarterId(TEST_MEMBER_ID).getCart();
		assertEquals("장바구니에서 삭제시 해당 품목은 NULL", cart.get(TEST_ITEM_ID), null);
	}
}
