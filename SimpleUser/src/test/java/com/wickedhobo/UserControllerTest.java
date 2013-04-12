package com.wickedhobo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class UserControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private static Logger log = LoggerFactory.getLogger(UserControllerTest.class);

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	@Transactional()
	//@Rollback(false)  //Left here for debug purposes.
	public void testAddUserController() throws Exception {
		mockMvc.perform(post("/addUser")
				.accept(MediaType.TEXT_HTML)
				.param("userName", "halgrena")
				.param("firstName", "Anne")
				.param("lastName", "halgren")
				.param("password", "password"))
				.andExpect(status().isOk())
				.andExpect(view().name("/result"))
				.andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
				.andExpect(model().attribute("addUser", "true"));
		log.debug("testAddUserController has passed all tests!");
	}
}
