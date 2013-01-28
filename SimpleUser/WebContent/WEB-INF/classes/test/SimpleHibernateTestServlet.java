package test;

import object.User;

import org.apache.cactus.ServletTestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.hibernateDAO.HibernateUtility;
import DAO.hibernateDAO.UserDAO;
import DAO.hibernateDAO.UserDAOImpl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SimpleHibernateTestServlet extends ServletTestCase {
	
	public SimpleHibernateTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(SimpleHibernateTestServlet.class);
	}
	
	public void testUserSave() {
		User user = new User();
		
		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		
		UserDAO userDAO = new UserDAOImpl(); 
		userDAO.save(user);
		
		//Now load without using DAO structure and compare
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		User user2 = (User) session.get(User.class, user.getUserName());
		tx.commit();
		assertNotNull(user2);
		assertEquals("halgrena", user2.getUserName());
	}
}
