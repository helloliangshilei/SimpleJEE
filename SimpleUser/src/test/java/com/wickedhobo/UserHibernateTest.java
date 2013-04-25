package com.wickedhobo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wickedhobo.DAO.hibernateDAO.HibUtil;
import com.wickedhobo.DAO.hibernateDAO.UserDAO;
import com.wickedhobo.DAO.hibernateDAO.UserDAOHibOnlyImpl;
import com.wickedhobo.object.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserHibernateTest {

	private static Logger log = LoggerFactory.getLogger(UserHibernateTest.class);

	@Test
	public void testUserSave() {
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userDAO.saveUser(user);

		// Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user2 = (User) session.get(User.class, user.getUserName());
		HibUtil.commitTx();
		HibUtil.closeSession();

		assertNotNull(user2);
		assertEquals("halgrena2", user2.getUserName());
		log.debug("testUserSave has passed all tests!");

		userDAO.deleteUser(user);
	}

	@Test
	public void testUserUpdate() {
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userDAO.saveUser(user);

		User user2 = new User();

		user2.setFirstName("Anne2");
		user2.setLastName("Halgren2");
		user2.setUserName("halgrena");
		user2.setPassword("anne3142");

		userDAO.updateUser(user2);

		// Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user3 = (User) session.get(User.class, user.getUserName());
		HibUtil.commitTx();
		HibUtil.closeSession();

		assertNotNull(user3);
		assertEquals("Halgren2", user2.getLastName());
		assertEquals("Anne2", user2.getFirstName());
		log.debug("testUserUpdate has passed all tests!");

		userDAO.deleteUser(user);
	}

	@Test
	public void testUserFind() {
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userDAO.saveUser(user);

		User user2 = userDAO.findUserByUsername("halgrena");

		assertEquals("Halgren", user2.getLastName());
		assertEquals("Anne", user2.getFirstName());
		log.debug("testUserFind has passed all tests!");

		userDAO.deleteUser(user);
	}

	@Test
	public void testUserDelete() {
		User user = new User();

		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userDAO.saveUser(user);

		userDAO.deleteUser(user);

		// Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user2 = (User) session.get(User.class, "halgrena");
		HibUtil.commitTx();
		HibUtil.closeSession();

		assertNull(user2);
		log.debug("testUserDelete has passed all tests!");
	}

	@Test
	//Sort of a bad test since it relies in state of DB from initial import, but it does what I want.
	public void testUserFindAll() {
		List<User> userList = new ArrayList<User>();

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userList = userDAO.listUsers();

		assertEquals(2, userList.size());
		log.debug("testUserFindAll has passed all tests!");
	}

	@Test
	//Sort of a bad test since it relies in state of DB from initial import, but it does what I want.
	public void testUserFindByRole() {
		List<User> userList = new ArrayList<User>();
		List<User> userList2 = new ArrayList<User>();

		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userList = userDAO.listUsersByRoles("developer");
		userList2 = userDAO.listUsersByRoles("administrator");

		assertEquals(1, userList.size());
		assertEquals(2, userList2.size());
		log.debug("testUserFindByRole has passed all tests!");
	}
}
