package com.example.bmm.domain.order;

import com.example.bmm.domain.product.Product;
import com.example.bmm.domain.user.User;
import com.example.bmm.model.dto.order.MyOrderDetailDto;
import com.example.bmm.model.dto.order.MyOrderSummaryDto;
import com.example.bmm.service.order.MyOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MyOrderServiceTest {
	@Mock
	private MyOrderRepository myOrderRepository;

	@InjectMocks
	private MyOrderService myOrderService;

	private Long TEST_ORDERER_ID = 1l;
	private Long TEST_ORDER_ID = 1l;

	@Test
	public void 내_주문_내역_가져오기() throws Exception {
		//given
		List<Order> content = List.of(createOrder());;
		PageRequest pageable = PageRequest.of(0, 1);
		int total = content.size();
		PageImpl<Order> orderList = new PageImpl<>(content, pageable, total);

		given(myOrderRepository.getMyOrders(anyLong(), any(Pageable.class)))
				.willReturn(orderList);

		//when
		MyOrderSummaryDto myOrderSummary = myOrderService.getMyOrderSummary(TEST_ORDERER_ID, pageable);

		//then
		assertNotNull("주문목록 조회 결과는 NULL이 아니다.", myOrderSummary);
		verify(myOrderRepository, atLeastOnce())
				.getMyOrders(TEST_ORDERER_ID, pageable);
	}

//	@Test
	public void 주문상세_가져오기() throws Exception {
		//given
		Order order = createOrder();

		given(myOrderRepository.getMyOrderDetails(TEST_ORDER_ID))
				.willReturn(Optional.of(order));

		//when
		MyOrderDetailDto myOrderDetails = myOrderService.getMyOrderDetails(TEST_ORDER_ID);

		//then
		assertNotNull("주문상세내역 조회 결과는 NULL이 아니다.", myOrderDetails);
		verify(myOrderRepository, atLeastOnce())
				.getMyOrderDetails(TEST_ORDER_ID);
	}

	private Order createOrder() {
		User user = User.builder()
				.name("TEST")
				.username("TestUser")
				.password("TEST")
				.build();
		ReflectionTestUtils.setField(user, "id", TEST_ORDERER_ID);

		Product product = Product.builder()
				.stock(2)
				.category("탕")
				.price(1000)
				.image("TEST")
				.name("TEST")
				.build();

		OrderProduct orderItem = OrderProduct.builder()
				.product(product)
				.orderCount(1)
				.build();

		Order orderEntity = Order.builder()
				.ordererId(user.getId())
				.orderProductList(List.of(orderItem))
				.build();

		ReflectionTestUtils.setField(orderEntity, "orderId", TEST_ORDER_ID);

		return  orderEntity;
	}
}
