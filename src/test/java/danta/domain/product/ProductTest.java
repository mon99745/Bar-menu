package danta.domain.product;

import danta.exception.NotEnoughStockQuantityException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	@Test
	public void 주문시_재고량_차감() throws Exception {
		//given
		int STOCK_QUANTITY = 10;
		Product product = createProduct(STOCK_QUANTITY);

		//when
		int ORDER_QUANTITY = 2;
		product.removeStockQuantity(ORDER_QUANTITY);

		//then
		assertThat(product.getStock(), is(STOCK_QUANTITY - ORDER_QUANTITY));
	}

	@Test
	public void 재고량을_초과하는_주문시_에러() throws Exception {
		//given
		int STOCK_QUANTITY = 10;
		Product product = createProduct(STOCK_QUANTITY);

		//when, then
		int OVER_ORDER_QUANTITY = 20;
		assertThrows(NotEnoughStockQuantityException.class,
				() -> product.removeStockQuantity(OVER_ORDER_QUANTITY));
	}

	@Test
	public void 재고량_추가() throws Exception {
		//given
		int STOCK_QUANTITY = 10;
		Product product = createProduct(STOCK_QUANTITY);

		//when
		int ADD_STOCK_QUANTITY = 10;
		product.addStockQuantity(ADD_STOCK_QUANTITY);

		//then
		assertThat(product.getStock(), is(STOCK_QUANTITY + ADD_STOCK_QUANTITY));
	}

	private Product createProduct(int stockQuantity) {
		return Product.builder()
				.category("탕")
				.image("TEST")
				.name("TEST")
				.price(1000)
				.stock(stockQuantity)
				.build();
	}

}
