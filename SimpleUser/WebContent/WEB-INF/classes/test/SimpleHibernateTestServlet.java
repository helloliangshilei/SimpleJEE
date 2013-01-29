package test;

import java.util.ArrayList;
import java.util.List;

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
		Session session = HibernateUtility.getSessionFactory().openSession();
		User user2 = (User) session.get(User.class, user.getUserName());
		session.close();
		assertNotNull(user2);
		assertEquals("halgrena", user2.getUserName());
	}
	
	public void testUserUpdatee() {
		User user = new User();
		
		user.setFirstName("Anne2");
		user.setLastName("Halgren2");
		user.setUserName("halgrena");
		user.setPassword("anne3142");
		
		UserDAO userDAO = new UserDAOImpl(); 
		userDAO.update(user);
		
		//Now load without using DAO structure and compare
		Session session = HibernateUtility.getSessionFactory().openSession();
		User user2 = (User) session.get(User.class, user.getUserName());
		session.close();
		assertNotNull(user2);
		assertEquals("Halgren2", user2.getLastName());
		assertEquals("Anne2", user2.getFirstName());
	}
	
	public void testUserFind() {
		User user = new User();
		
		UserDAO userDAO = new UserDAOImpl(); 
		user = userDAO.find(User.class, "halgrena");
		
		assertEquals("Halgren2", user.getLastName());
		assertEquals("Anne2", user.getFirstName());
	}
	
	public void testUserDelete() {
		User user = new User();
		
		user.setUserName("halgrena");
		
		UserDAO userDAO = new UserDAOImpl(); 
		userDAO.delete(user);
		
		//Now load without using DAO structure and compare
		Session session = HibernateUtility.getSessionFactory().openSession();
		User user2 = (User) session.get(User.class, "halgrena");
		
		session.close();
		assertNull(user2);
	}
	
	public void testUserFindAll() {
		List<User> userList = new ArrayList<User>(); 
		
		UserDAO userDAO = new UserDAOImpl();
		userList = userDAO.findAll(User.class);
		for (int i = 0; i < userList.size(); i++) {
			User user = (User)userList.get(i);
			System.out.println(user.getLastName());
		}
		
	}
	
}
