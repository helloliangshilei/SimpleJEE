package com.wickedhobo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wickedhobo.DAO.hibernateDAO.UserDAOHibSpringImpl;
import com.wickedhobo.object.Role;
import com.wickedhobo.object.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserSpringHibernateTest {

	private static Logger log = LoggerFactory.getLogger(UserSpringHibernateTest.class);

	@Autowired
	UserDAOHibSpringImpl userDAO;

	@Test
	@Transactional()
	public void testUserSave() {
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

		User user2 = userDAO.findUserByUsername("halgrena");

		assertNotNull(user2);
		assertEquals("halgrena", user2.getUserName());
		log.debug("testUserSave has passed all tests!");
	}

	@Test
	@Transactional()
	public void testUserUpdate() {
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

		User user2 = userDAO.findUserByUsername("halgrena");

		user2.setFirstName("Anne2");
		user2.setLastName("Halgren2");
		user2.setPassword("anne3142");

		userDAO.updateUser(user2);

		User user3 = userDAO.findUserByUsername("halgrena");

		assertNotNull(user3);
		assertEquals("Halgren2", user3.getLastName());
		assertEquals("Anne2", user3.getFirstName());
		log.debug("testUserUpdate has passed all tests!");
	}

	@Test
	@Transactional()
	public void testUserFind() {

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

		User user2 = userDAO.findUserByUsername("halgrena");

		assertEquals(1, user2.getRoles().size());
		assertEquals("Halgren", user2.getLastName());
		assertEquals("Anne", user2.getFirstName());
		log.debug("testUserFind has passed all tests.");
	}

	@Test
	@Transactional()
	public void testUserDelete() {
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

		User user2 = userDAO.findUserByUsername("halgrena");
		userDAO.deleteUser(user2);
		
		User user3 = userDAO.findUserByUsername("halgrena");
		
		assertNull(user3);
		log.debug("testUserDelete has passed all tests!");
	}

	@Test
	@Transactional()
	public void testUserFindAll() {
		List<User> userList = new ArrayList<User>();

		userList = userDAO.listUsers();

		for (User user : userList) {
			log.debug("Last Name (Find All): " + user.getLastName());
			for (Role role : user.getRoles()) {
				log.debug("   (testUserfindAll) Roles List 1: " + role.getRole());
			}
		}
		assertEquals(2, userList.size());
		log.debug("testUserFindAll has passed all tests!");
	}

	@Test
	@Transactional()
	public void testUserFindByRole() {
		List<User> userList = new ArrayList<User>();
		List<User> userList2 = new ArrayList<User>();

		userList = userDAO.listUsersByRoles("developer");
		userList2 = userDAO.listUsersByRoles("administrator");

		for (User user : userList) {
			log.debug("(testUserFindByRole) Last Name List 1 findByRole: " + user.getLastName());
			for (Role role : user.getRoles()) {
				log.debug("   (testUserFindByRole)Roles List 1: " + role.getRole());
			}
		}

		for (User user : userList2) {
			log.debug("(testUserFindByRole) Last Name List 2 findByRole: " + user.getLastName());
			for (Role role : user.getRoles()) {
				log.debug("   (testUserFindByRole) Roles List 2: " + role.getRole());
			}
		}
		assertEquals(1, userList.size());
		assertEquals(2, userList2.size());
		log.debug("testUserFindByRole has passed all tests!");
	}
}