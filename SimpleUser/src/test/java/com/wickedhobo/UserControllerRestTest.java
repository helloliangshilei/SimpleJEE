package com.wickedhobo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/rest-servlet.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class UserControllerRestTest {
	
	@Autowired
	private WebApplicationContext ctx;
	@Autowired
	UserDAOHibSpringImpl userDAO;
	private MockMvc mockMvc;
	private static Logger log = LoggerFactory.getLogger(UserControllerTest.class);
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	@Transactional()
	//@Rollback(false) //Left here for debug purposes.
	public void testAddUserController() throws Exception {
		mockMvc.perform(post("/addUser/" +
												 "userName/{userName}/" +
												 "firstName/{firstName}/" +
												 "lastName/{lastName}/" +
												 "password/{password}", 
												 "halgrena", 
												 "Anne", 
												 "Halgren", 
												 "anne314")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		
		User user2 = userDAO.findUserByUsername("halgrena");
		assertNotNull(user2);
		assertEquals("Halgren", user2.getLastName());
		assertEquals("Anne", user2.getFirstName());
		assertEquals("anne314", user2.getPassword());
		log.debug("UserControllerRestTest.testAddUserController has passed all tests!");		
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
		
		mockMvc.perform(post("/updateUser/" +
				 "userName/{userName}/" +
				 "firstName/{firstName}/" +
				 "lastName/{lastName}/" +
				 "password/{password}", 
				 "halgrena", 
				 "Anne2", 
				 "Halgren2", 
				 "anne3142")
				 .accept(MediaType.APPLICATION_JSON))
				 .andDo(print())
				 .andExpect(status().isOk());

		User user2 = userDAO.findUserByUsername("halgrena");
		assertNotNull(user2);
		assertEquals("Halgren2", user2.getLastName());
		assertEquals("Anne2", user2.getFirstName());
		assertEquals("anne3142", user2.getPassword());
		log.debug("UserControllerRestTest.testUpdateUserController has passed all tests!");
		
	}
	
	@Test
	@Transactional()
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
		
		mockMvc.perform(post("/removeUser/" +
				 								 "userName/{userName}/",
				 								 "halgrena") 
												 .accept(MediaType.APPLICATION_JSON))
												 .andDo(print())
												 .andExpect(status().isOk());
		
		User user2 = userDAO.findUserByUsername("halgrena");
		assertNull(user2);
		log.debug("UserControllerRestTest.testRemoveUserController has passed all tests!");
	}
		
	@Test
	@Transactional()
	public void testFindUserByUsernameController() throws Exception {

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

		mockMvc.perform(get("/findUserByUsername/{userName}", user.getUserName()).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("userName", equalTo("halgrena")))
				.andExpect(jsonPath("firstName", equalTo("Anne")))
				.andExpect(jsonPath("lastName", equalTo("Halgren")))
				.andExpect(jsonPath("password", equalTo("anne314")));
		log.debug("UserControllerRestTest.testFindUserByUsernameController has passed all tests!");
	}
	
	@Test
	@Transactional()
	public void testFindUserByUsernameWithActionController() throws Exception {

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

		mockMvc.perform(get("/findUserByUsernameWithAction/{userName}", user.getUserName())
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(view().name("/result"))
				.andExpect(model().attribute("userAction", "findUserByUsername"))
				.andExpect(model().attribute("user", hasProperty("firstName", equalTo("Anne"))))
				.andExpect(model().attribute("user", hasProperty("lastName", equalTo("Halgren"))))
				.andExpect(model().attribute("user", hasProperty("password", equalTo("anne314"))));
		log.debug("UserControllerRestTest.testFindUserByUsernameWithActionController has passed all tests!");
	}
	
	
	
}
