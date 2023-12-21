package com.example.bmm.controller.product;

import com.example.bmm.config.ControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.bmm.controller.product.ProductRestController.PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@Slf4j
@ControllerTest
class ProductRestControllerTest {

	private static final MockHttpSession SESSION = new MockHttpSession();

	@Autowired
	private MockMvc mvc;
	private static Long id = 999L;

	private static String createMsg = "{\n" +
			"  \"id\" : \""+ id +"\",\n" +
			"  \"category\" : \"탕\",\n" +
			"  \"name\" : \"Test menu\",\n" +
			"  \"price\" : \"10000\",\n" +
			"  \"status\" : \"true\",\n" +
			"  \"image\" : \"Test.path\",\n" +
			"  \"description\" : \"Test\",\n" +
			"  \"stock\" : \"100\"\n" +
			"\n" +
			"}";

	private static String updateMsg = "{\n" +
			"  \"id\" : \"1\",\n" +
			"  \"category\" : \"탕\",\n" +
			"  \"name\" : \"Test menu\",\n" +
			"  \"price\" : \"15000\",\n" +
			"  \"status\" : \"true\",\n" +
			"  \"image\" : \"Test.path\",\n" +
			"  \"description\" : \"Test\",\n" +
			"  \"stock\" : \"100\"\n" +
			"\n" +
			"}";
	@Test
	void t01create() throws Exception{
		mvc.perform(post(PATH + "/create")
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(createMsg))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(r -> createMsg = r.getResponse().getContentAsString());

		JSONObject object = new JSONObject(createMsg);
		id = Long.valueOf(String.valueOf(object.get("id")));
	}

	@Test
	void t02read() throws Exception{
		mvc.perform(get(PATH + "/read/{productId}", id)
						.session(SESSION))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void t03update() throws Exception{
		mvc.perform(post(PATH + "/update/{productId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(updateMsg))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price", equalTo(15000)));
	}

	@Test
	void t04delete() throws Exception{
		mvc.perform(post(PATH + "/delete/{productId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(createMsg))
				.andDo(print())
				.andExpect(status().isOk());
	}
}