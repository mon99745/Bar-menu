package com.example.bma.controller;

import com.example.bma.cotroller.admin.AdminRestController;
import com.example.bmm.config.ControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

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
class AdminRestControllerTest {

	private static final MockHttpSession SESSION = new MockHttpSession();

	@Autowired
	private MockMvc mvc;
	private static String createMsg = "{\n" +
			"    \"adminname\" : \"adminTest\",\n" +
			"    \"password\" : \"0000\",\n" +
			"    \"name\" : \"TestAdmin\",\n" +
			"    \"role\" : \"ADMIN\"\n" +
			"}";

	private static String updateMsg = "{\n" +
			"    \"adminname\": \"adminTest\",\n" +
			"    \"password\": \"$2a$10$4fLQaY81IRBwdTPO7dR1t.HoQBwoT1IacZhCHmq1V//WVTOxhIQMK\",\n" +
			"    \"name\": \"ChangeName\",\n" +
			"    \"regDate\": \"2023-06-29T13:22:54.7724378\",\n" +
			"    \"modDate\": \"2023-06-29T13:22:54.7724378\",\n" +
			"    \"id\": 1,\n" +
			"    \"status\": \"TRUE\",\n" +
			"    \"role\": \"ADMIN\",\n" +
			"    \"roleKey\": \"ROLE_ADMIN\"\n" +
			"}";

	private static Long id = 0L;

	@Test
	void t05create() throws Exception {
		mvc.perform(post(AdminRestController.PATH + "/create")
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
	void t06read() throws Exception {
		mvc.perform(get(AdminRestController.PATH + "/read/{adminId}", id)
						.session(SESSION))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void t07update() throws Exception {
		mvc.perform(put(AdminRestController.PATH + "/update/{adminId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(updateMsg))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("ChangeName")));
	}

	@Test
	void t08delete() throws Exception {
		mvc.perform(delete(AdminRestController.PATH + "/delete/{adminId}", id)
						.session(SESSION)
						.contentType(MediaType.APPLICATION_JSON)
						.content(createMsg))
				.andDo(print())
				.andExpect(status().isOk());
	}
}