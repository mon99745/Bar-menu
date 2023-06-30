package danta.domain.cart;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class CartTest {
	private Long CART_ID = 1l;
	private Long CART_ITEM_ID = 1l;

	@Test
	public void addProductToCart() throws Exception {
		//given
		Cart cart = createCart();
		int ORDER_COUNT = 1;
		int TARGET_STOCK_QUANTITY = 2;
		CartLine cartLine = new CartLine(CART_ID, CART_ITEM_ID, ORDER_COUNT);

		//when
		cart.addProductToCart(TARGET_STOCK_QUANTITY, cartLine);

		//then
		assertThat(cart.getCart().get(CART_ITEM_ID).getOrderCount(), is(ORDER_COUNT));
	}

	@Test
	public void modifyOrderCount() throws Exception {
		//given
		Cart cart = createCart();
		int BEFORE_ORDER_COUNT = 1;
		cart.addProductToCart(100, new CartLine(CART_ID, CART_ITEM_ID, BEFORE_ORDER_COUNT));

		//when
		int NEW_ORDER_COUNT = 2;
		cart.modifyOrderCount(100, new CartLine(CART_ID, CART_ITEM_ID, NEW_ORDER_COUNT));

		//then
		assertThat(cart.getCart().get(CART_ITEM_ID).getOrderCount(), is(NEW_ORDER_COUNT));
	}

	@Test
	public void removeCartLine() throws Exception {
		//given
		Cart cart = createCart();
		cart.addProductToCart(100, new CartLine(CART_ID, CART_ITEM_ID, 1));

		//when
		cart.removeCartLine(CART_ITEM_ID);

		//then
		assertThat(cart.getCart().containsKey(CART_ITEM_ID), is(false));
	}

	private Cart createCart() {
		Cart cart = new Cart(1l);
		ReflectionTestUtils.setField(cart, "cartId", CART_ID);
		return cart;
	}
}
