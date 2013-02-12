package test;

import java.util.ArrayList;
import java.util.List;

import object.User;

import org.apache.cactus.ServletTestCase;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.hibernateDAO.HibUtil;
import DAO.hibernateDAO.UserDAO;

import junit.framework.Test;
import junit.framework.TestSuite;

public class HibernateSpringDAOTestServlet extends ServletTestCase {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
	
	public HibernateSpringDAOTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(HibernateSpringDAOTestServlet.class);
	}
	
	public void testUserSave() {
		User user = new User();
		
		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		userDAO.saveUser(user);
		((ClassPathXmlApplicationContext) context).close(); 
		
		//Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user2 = (User) session.get(User.class, user.getUserName());
		HibUtil.commitTx();
		HibUtil.closeSession();
		
		assertNotNull(user2);
		assertEquals("halgrena", user2.getUserName());
	}
	
	public void testUserUpdatee() {
		User user = new User();
		
		user.setFirstName("Anne2");
		user.setLastName("Halgren2");
		user.setUserName("halgrena");
		user.setPassword("anne3142");
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		userDAO.updateUser(user);
		((ClassPathXmlApplicationContext) context).close(); 
		
		//Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user3 = (User) session.get(User.class, user.getUserName());
		HibUtil.commitTx();
		HibUtil.closeSession();

		assertNotNull(user3);
		assertEquals("Halgren2", user3.getLastName());
		assertEquals("Anne2", user3.getFirstName());
	}
	
	public void testUserFind() {
		User user = new User();
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO"); 
		user = userDAO.findUserByUsername("halgrena");
		
		assertEquals("Halgren2", user.getLastName());
		assertEquals("Anne2", user.getFirstName());
	}
	
	public void testUserDelete() {
		User user = new User();
		
		user.setUserName("halgrena");
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		userDAO.update(user);
		userDAO.deleteUser(user);
		
		//Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		HibUtil.beginTx();
		User user2 = (User) session.get(User.class, "halgrena");
		HibUtil.commitTx();
		HibUtil.closeSession();
		
		assertNull(user2);
	}
	
	public void testUserFindAll() {
		List<User> userList = new ArrayList<User>(); 
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		userList = userDAO.listUsers();
		
		//Just for giggles.
		for (User user : userList) {
			System.out.println("Last Name: " + user.getLastName());
    }
		
		assertEquals(2, userList.size());
	}
	
	public void testUserFindByRole() {
		List<User> userList = new ArrayList<User>();
		List<User> userList2 = new ArrayList<User>();
		
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		userList = userDAO.listUsersByRoles("developer");
		userList2 = userDAO.listUsersByRoles("administrator");
		
		for (User user : userList) {
			System.out.println("Last Name List 1 findByRole: " + user.getLastName());
    }
		
		for (User user : userList2) {
			System.out.println("Last Name List 2 findByRole: " + user.getLastName());
    }
		
		assertEquals(1, userList.size());
		assertEquals(2, userList2.size());
	}
}
