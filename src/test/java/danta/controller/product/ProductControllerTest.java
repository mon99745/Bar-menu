package danta.controller.product;

import danta.domain.product.Product;
import danta.model.dto.product.ProductDetailDto;
import danta.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = ProductController.class)
class ProductControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ProductService productService;

	private Long productId = 1l;

	private Product createProduct() {
		return Product.builder()
				.image("test")
				.name("test")
				.price(1000)
				.stock(1)
				.build();
	}

	@Test
	public void 아이템_생성_페이지() throws Exception {
		//given
		given(productService.saveProduct(any()))
				.willReturn(productId);

		// when
		ResultActions resultActions = mvc.perform(get("/products/new"));

		// then
		resultActions
				.andExpect(model().attributeExists("form"))
				.andExpect(view().name("products/registerProductForm"))
				.andExpect(status().isOk());
	}

	@Test
	public void 아이템_생성() throws Exception {
		//given
		given(productService.saveProduct(any()))
				.willReturn(productId);

		//when
		ResultActions resultActions = mvc.perform(post("/products/new")
				.param("name", "TEST")
				.param("imagePath", "TEST")
				.param("price", "1000")
				.param("stockQuantity", "1")
		);

		//then
		resultActions
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/products/" + productId));
	}

	@Test
	public void 아이템_상세_페이지() throws Exception {
		//given
		given(productService.findProduct(productId))
				.willReturn(new ProductDetailDto(createProduct()));

		ProductDetailDto product = productService.findProduct(productId);

		//when
		ResultActions resultActions = mvc.perform(get("/products/{productId}", productId));

		//then
		resultActions
				.andExpect(status().isOk())
				.andExpect(model().attribute("product", product))
				.andExpect(view().name("products/productDetails"));
	}
}
