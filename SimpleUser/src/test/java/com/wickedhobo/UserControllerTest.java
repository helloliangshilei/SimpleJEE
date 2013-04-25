package com.wickedhobo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
  private MockMvc mockMvc;
  private static Logger log = LoggerFactory.getLogger(UserControllerTest.class);

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
  }

  @Test
  @Transactional()
  // @Rollback(false) //Left here for debug purposes.
  public void testAddUserController() throws Exception {
    mockMvc.perform(post("/addUser")
        .accept(MediaType.APPLICATION_FORM_URLENCODED)
        .param("userName", "halgrena").param("firstName", "Anne")
        .param("lastName", "Halgren").param("password", "password"))
        .andDo(print())
        .andExpect(status().isOk()).andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "addUser"));
    log.debug("UserControllerTest.testAddUserController has passed all tests!");
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
        .accept(MediaType.APPLICATION_FORM_URLENCODED)
        .param("userName", "halgrena")
        .param("firstName", "Anne2")
        .param("lastName", "Halgren2")
        .param("password", "password2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "updateUser"));

    User user2 = userDAO.findUserByUsername("halgrena");

    assertNotNull(user2);
    assertEquals("Halgren2", user2.getLastName());
    assertEquals("Anne2", user2.getFirstName());
    assertEquals("password2", user2.getPassword());
    log.debug("UserControllerTest.testUpdateUserController has passed all tests!");
  }

  @Test
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
        .accept(MediaType.APPLICATION_FORM_URLENCODED)
        .param("userName", "halgrena"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "removeUser"));

    User user2 = userDAO.findUserByUsername("halgrena");

    assertNull(user2);
    log.debug("UserControllerTest.testRemoveUserController has passed all tests!");
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

    mockMvc.perform(get("/findUserByUsername")
        .param("userName", "halgrena"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "findUserByUsername"))
        .andExpect(model().attribute("user", hasProperty("firstName", equalTo("Anne"))))
        .andExpect(model().attribute("user", hasProperty("lastName", equalTo("Halgren"))))
        .andExpect(model().attribute("user", hasProperty("password", equalTo("anne314"))));
    log.debug("UserControllerTest.testFindUserByUsernameController has passed all tests!");
  }

  @Test
  @Transactional
  // I know, test depends on external data, but source the creatStuff.sql file
  // and test passes.
  // Hey, it's an integration test. Integrate.
  public void testListUsersController() throws Exception {
    mockMvc.perform(get("/listUsers"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "listUsers"))
        .andExpect(model().attribute("userList", hasSize(2)))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("mckerrj")),
                hasProperty("firstName", equalTo("Jason")),
                hasProperty("lastName", equalTo("McKerr"))))))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("gordond")),
                hasProperty("firstName", equalTo("Dexter")),
                hasProperty("lastName", equalTo("Gordon"))))));
    log.debug("UserControllerTest.testListUsersController has passed all tests!");
  }

  @Test
  @Transactional
  // I know, test depends on external data, but source the creatStuff.sql file
  // and test passes.
  // Hey, it's an integration test. Integrate.
  public void testListUsersByRoleController() throws Exception {
    mockMvc.perform(get("/listUsersByRole")
        .param("roleName", "administrator"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "listUsersByRole"))
        .andExpect(model().attribute("userList", hasSize(2)))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("mckerrj")),
                hasProperty("firstName", equalTo("Jason")),
                hasProperty("lastName", equalTo("McKerr"))))))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("gordond")),
                hasProperty("firstName", equalTo("Dexter")),
                hasProperty("lastName", equalTo("Gordon"))))));

    mockMvc.perform(get("/listUsersByRole")
        .param("roleName", "developer"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "listUsersByRole"))
        .andExpect(model().attribute("userList", hasSize(1)))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("mckerrj")),
                hasProperty("firstName", equalTo("Jason")),
                hasProperty("lastName", equalTo("McKerr"))))));

    mockMvc.perform(get("/listUsersByRole")
        .param("roleName", "saxplayer"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/result"))
        .andExpect(forwardedUrl("/WEB-INF/JSP/result.jsp"))
        .andExpect(model().attribute("userAction", "listUsersByRole"))
        .andExpect(model().attribute("userList", hasSize(1)))
        .andExpect(model().attribute("userList", hasItem(
            allOf(
                hasProperty("userName", equalTo("gordond")),
                hasProperty("firstName", equalTo("Dexter")),
                hasProperty("lastName", equalTo("Gordon"))))));
    log.debug("UserControllerTest.testListUsersByRoleController has passed all tests!");
  }
}
