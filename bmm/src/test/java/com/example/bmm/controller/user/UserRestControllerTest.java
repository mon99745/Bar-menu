package com.example.bmm.controller.user;

import com.example.bmm.config.ControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.bmm.controller.user.UserRestController.PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@Slf4j
@ControllerTest
class UserRestControllerTest {
	private static final MockHttpSession SESSION = new MockHttpSession();

	@Autowired
	private MockMvc mvc;
	private static String createMsg = "{\n" +
			"    \"username\" : \"userTest\",\n" +
			"    \"password\" : \"0000\",\n" +
			"    \"name\" : \"TestUser\",\n" +
			"    \"role\" : \"USER\"\n" +
			"}";

	private static String updateMsg = "{\n" +
			"    \"username\": \"userTest\",\n" +
			"    \"password\": \"$2a$10$4fLQaY81IRBwdTPO7dR1t.HoQBwoT1IacZhCHmq1V//WVTOxhIQMK\",\n" +
			"    \"name\": \"ChangeName\",\n" +
			"    \"regDate\": \"2023-06-29T13:22:54.7724378\",\n" +
			"    \"modDate\": \"2023-06-29T13:22:54.7724378\",\n" +
			"    \"id\": 1,\n" +
			"    \"status\": \"TRUE\",\n" +
			"    \"role\": \"USER\",\n" +
			"    \"roleKey\": \"ROLE_USER\"\n" +
			"}";

	private static Long id = 0L;

	@Test
	void t01create() throws Exception {
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
	void t02read() throws Exception {
		mvc.perform(get(PATH + "/read/{userId}", id)
						.session(SESSION))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void t03update() throws Exception {
		mvc.perform(put(PATH + "/update/{userId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(updateMsg))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("ChangeName")));
	}

	@Test
	void t04delete() throws Exception {
		mvc.perform(delete(PATH + "/delete/{userId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(createMsg))
				.andDo(print())
				.andExpect(status().isOk());
	}
}