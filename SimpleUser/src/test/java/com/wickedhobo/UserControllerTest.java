package com.wickedhobo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.wickedhobo.DAO.hibernateDAO.UserDAOHibSpringImpl;
import com.wickedhobo.object.Role;
import com.wickedhobo.object.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class UserControllerTest {

	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	UserDAOHibSpringImpl userDAO;

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
				.param("lastName", "Halgren")
				.param("password", "password"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("/result"))
				.andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
				.andExpect(model().attribute("addUser", "true"));
		log.debug("testAddUserController has passed all tests!");
	}
	
	@Test
	@Transactional()
	public void testUpdateUserController() throws Exception {
		
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		Role role = new Role();
		role.setRole("administrator");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);

		userDAO.saveUser(user);
		
		mockMvc.perform(post("/updateUser")
				.accept(MediaType.TEXT_HTML)
				.param("userName", "halgrena")
				.param("firstName", "Anne2")
				.param("lastName", "Halgren2")
				.param("password", "password2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("/result"))
				.andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
				.andExpect(model().attribute("updateUser", "true"));
		
		User user3 = userDAO.findUserByUsername("halgrena");

		assertNotNull(user3);
		assertEquals("Halgren2", user3.getLastName());
		assertEquals("Anne2", user3.getFirstName());
		assertEquals("password2", user3.getPassword());
		log.debug("testUpdateUserController has passed all tests!");
	}
	
	@Test
	//@Transactional() //Doing sequential remove, so don't want Transactional
	public void testRemoveUserController() throws Exception {
		
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		Role role = new Role();
		role.setRole("administrator");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);

		userDAO.saveUser(user);
		
		mockMvc.perform(post("/removeUser")
				.accept(MediaType.TEXT_HTML)
				.param("userName", "halgrena"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("/result"))
				.andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
				.andExpect(model().attribute("removeUser", "true"));
		
		User user2 = userDAO.findUserByUsername("halgrena");

		assertNull(user2);
		log.debug("testRemoveUserController has passed all tests!");
	}
	
	
}
