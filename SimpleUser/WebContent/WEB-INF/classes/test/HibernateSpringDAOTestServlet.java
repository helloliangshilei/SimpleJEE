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
	
	public HibernateSpringDAOTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(HibernateSpringDAOTestServlet.class);
	}
	
	public void testUserSave() {
		User user = new User();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
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
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		user.setFirstName("Anne2");
		user.setLastName("Halgren2");
		user.setUserName("halgrena");
		user.setPassword("anne3142");
		
		UserDAO userDAO2 = (UserDAO)context.getBean("userDAO");
		userDAO2.updateUser(user);
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
	
	/*public void testUserFind() {
		User user = new User();
		
		UserDAO userDAO = new UserDAOHibOnlyImpl(); 
		user = userDAO.findUserByUsername("halgrena");
		
		assertEquals("Halgren2", user.getLastName());
		assertEquals("Anne2", user.getFirstName());
	}*/
	
	/*public void testUserDelete() {
		User user = new User();
		
		user.setUserName("halgrena");
		
		UserDAO userDAO = new UserDAOHibOnlyImpl(); 
		userDAO.deleteUser(user);
		
		//Now load without using DAO structure and compare
		Session session = HibUtil.getSessionFactory().openSession();
		User user2 = (User) session.get(User.class, "halgrena");
		
		session.close();
		assertNull(user2);
	}*/
	
	/*public void testUserFindAll() {
		List<User> userList = new ArrayList<User>(); 
		
		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userList = userDAO.listUsers();
		
		assertEquals(2, userList.size());
	}*/
	
	/*public void testUserFindByRole() {
		List<User> userList = new ArrayList<User>();
		List<User> userList2 = new ArrayList<User>();
		
		UserDAO userDAO = new UserDAOHibOnlyImpl();
		userList = userDAO.listUsersByRoles("developer");
		userList2 = userDAO.listUsersByRoles("administrator");
		
		assertEquals(1, userList.size());
		assertEquals(2, userList2.size());
	}*/
}
