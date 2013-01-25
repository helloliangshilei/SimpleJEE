package test;

import junit.framework.Test;
import junit.framework.TestSuite;

import object.User;

import org.apache.cactus.ServletTestCase;

import commonDAO.UserDAOFactory;

import simpleDAO.UserDAO;

public class SimpleDAOTestServlet extends ServletTestCase {
	
	public SimpleDAOTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(SimpleDAOTestServlet.class);
	}
	
	public void testUserGet() {
		UserDAO userDAOGet = UserDAOFactory.getUserDAO("jdbc");
		User userGet = new User();
		try {
			userGet = userDAOGet.getUser("mckerrj");
		}
		catch (Exception e) {
			fail("Test failed with exception: " + e);
		}
		assertEquals("mckerrj", userGet.getUserName());
		assertEquals("Jason", userGet.getFirstName());
		assertEquals("McKerr", userGet.getLastName());
		assertEquals("mckerrj314", userGet.getPassword());
	}
}
