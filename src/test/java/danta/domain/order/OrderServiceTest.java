package danta.domain.order;

import danta.domain.product.Product;
import danta.domain.product.ProductRepository;
import danta.domain.user.User;
import danta.exception.NotEnoughStockQuantityException;
import danta.model.dto.order.OrderLineRequest;
import danta.model.dto.order.OrderRequest;
import danta.service.cart.CartService;
import danta.service.order.OrderService;
import danta.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
	@InjectMocks
	private OrderService orderService;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private UserService userSerivce;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private CartService cartService;

	@Test
	// ordererId, orderRequest를 매개변수로 받아 orderRepository.save를 orderRepository.save 호출 했는지 확인
	public void createOrder() {
		//given
		// 주문자
		Long TEST_USER_ID = 1l;
		User TEST_USER = createUser();
		given(userSerivce.findUser(TEST_USER_ID))
				.willReturn(TEST_USER);

		// 주문 상품
		Long TEST_PRODUCT_ID = 2l;
		int stock = 2;
		Product TEST_PRODUCT = createProduct(stock);
		ReflectionTestUtils.setField(TEST_PRODUCT, "id", TEST_PRODUCT_ID);
		given(productRepository.findById(TEST_PRODUCT_ID))
				.willReturn(Optional.of(TEST_PRODUCT));

		// 장바구니 비우기
		willDoNothing()
				.given(cartService)
				.removeCartLines(any(), any());

		// 주문 요청
		int orderCount = 2;
		OrderRequest orderRequest = createOrderRequest(TEST_PRODUCT_ID, orderCount);

		// 주문
		Order TEST_ORDER = createOrder(TEST_USER, TEST_PRODUCT, orderCount);
		given(orderRepository.save(any(Order.class)))
				.willReturn(TEST_ORDER);

		//when
		Long orderId = orderService.order(TEST_USER_ID, orderRequest);

		//then
		verify(orderRepository, atLeastOnce())
				.save(any(Order.class));
		verify(cartService, atLeastOnce())
				.removeCartLines(any(), any());
	}

	private Order createOrder(User TEST_USER, Product TEST_PRODUCT, int orderCount) {
		Order TEST_ORDER = Order.builder()
				.ordererId(TEST_USER.getId())
				.orderProductList(List.of(
						OrderProduct.builder()
								.orderCount(orderCount)
								.product(TEST_PRODUCT)
								.build()
				))
				.build();
		Long TEST_ORDER_ID = 1l;
		ReflectionTestUtils.setField(TEST_ORDER, "orderId", TEST_ORDER_ID);

		return TEST_ORDER;
	}

	private OrderRequest createOrderRequest(Long productId, int orderCount) {
		List<OrderLineRequest> orderLineRequests = List.of(new OrderLineRequest(productId, orderCount));

		OrderRequest orderRequest = new OrderRequest(orderLineRequests);

		return orderRequest;
	}

	@Test
	public void OutOfStockError() throws Exception {
		//given
		Long TEST_USER_ID = 1l;
		User TEST_USER = createUser();
		ReflectionTestUtils.setField(TEST_USER, "id", TEST_USER_ID);
		given(userSerivce.findUser(any()))
				.willReturn(TEST_USER);

		Long TEST_PRODUCT_ID = 2l;
		int stock = 1;
		Product TEST_PRODUCT = createProduct(stock);
		ReflectionTestUtils.setField(TEST_PRODUCT, "id", TEST_PRODUCT_ID);
		given(productRepository.findById(any()))
				.willReturn(Optional.of(TEST_PRODUCT));

		//when
		int orderCount = 2;

		//then
		assertThrows(NotEnoughStockQuantityException.class,
				() -> orderService.order(TEST_USER.getId(), createOrderRequest(TEST_PRODUCT_ID, orderCount)));
	}

	@Test
	public void orderCancel() throws Exception {
		//given
		Long TEST_ORDER_ID = 1l;
		Order TEST_ORDER = mock(Order.class);
		willDoNothing()
				.given(TEST_ORDER).cancel();
		given(orderRepository.findById(any()))
				.willReturn(Optional.of(TEST_ORDER));

		//when
		orderService.cancel(TEST_ORDER_ID);

		//then
		verify(TEST_ORDER, atLeastOnce())
				.cancel();
	}

	private Product createProduct(int stock) {
		Product TEST_PRODUCT = Product.builder()
				.name("TEST")
				.price(1000)
				.stock(stock)
				.image("TEST")
				.category("탕")
				.build();
		return TEST_PRODUCT;
	}

	private User createUser() {
		User TEST_USER = User.builder()
				.username("TEST")
				.password("TEST")
//				.address(new Address("TEST", "TEST"))
				.build();

		return TEST_USER;
	}
}
