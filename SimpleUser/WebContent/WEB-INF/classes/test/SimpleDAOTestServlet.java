package test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import object.User;

import org.apache.cactus.ServletTestCase;

import simpleDAO.UserDAOSimple;
import simpleDAO.UserDAOFactory;

/*
 * I know that my tests are chained rather than proper setup and breakdown for each
 * but it's just a sample app. :)
 */

public class SimpleDAOTestServlet extends ServletTestCase {

	public SimpleDAOTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(SimpleDAOTestServlet.class);
	}

	public void testUserGet() {
		User userGet = new User();
		
		try {
			UserDAOSimple userDAOGet = UserDAOFactory.getUserDAO("jdbc");
			userGet = userDAOGet.find("mckerrj");
		} 
		catch (Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals("mckerrj", userGet.getUserName());
		assertEquals("Jason", userGet.getFirstName());
		assertEquals("McKerr", userGet.getLastName());
		assertEquals("mckerrj314", userGet.getPassword());
	}

	public void testUserAdd() {
		int add = 0;

		try {
			UserDAOSimple userDAOAdd = UserDAOFactory.getUserDAO("jdbc");
			User userAdd = new User();
			userAdd.setUserName("tarantinoq");
			userAdd.setFirstName("Quentin");
			userAdd.setLastName("Tarantino");
			userAdd.setPassword("asdfasdf");
			add = userDAOAdd.save(userAdd);
		}
		catch (Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals(1, add);
	}
	
	public void testUserChange() {
		int change = 0;
		
		try {
			UserDAOSimple userDAOChange = UserDAOFactory.getUserDAO("jdbc");
			User userChange = new User();
			userChange.setUserName("tarantinoq");
			userChange.setFirstName("Quentin");
			userChange.setLastName("TarantinoChanged");
			userChange.setPassword("asdfasdf");
			change = userDAOChange.change(userChange);
		}
		catch(Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals(1, change);
	}
	
	public void testUserList() {
		int size = 0;
		
		try {
			UserDAOSimple userDAOList = UserDAOFactory.getUserDAO("jdbc");
			List<User> userList = userDAOList.listAll();
			size = userList.size();
		}
		catch (Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals(3, size);
	}
	
	public void testUserRemove() {
		int remove = 0;

		try {
			UserDAOSimple userDAORemove = UserDAOFactory.getUserDAO("jdbc");
			User userRemove = new User();
			userRemove.setUserName("tarantinoq");
			remove = userDAORemove.remove(userRemove);
		}
		catch (Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals(1, remove);
	}
}
