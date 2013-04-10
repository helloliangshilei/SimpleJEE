package com.wickedhobo.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wickedhobo.object.Role;
import com.wickedhobo.object.User;

import org.apache.cactus.ServletTestCase;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wickedhobo.DAO.hibernateDAO.HibUtil;
import com.wickedhobo.DAO.hibernateDAO.UserDAO;

import junit.framework.Test;
import junit.framework.TestSuite;

public class HibernateSpringDAOTestServlet extends ServletTestCase {
	
	private static Logger log = LoggerFactory.getLogger(HibernateSpringDAOTestServlet.class);
	
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
		Role role = new Role();
		role.setRole("administrator");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		
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
		//Load object
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		user = userDAO.findUserByUsername("halgrena");
		
		user.setFirstName("Anne2");
		user.setLastName("Halgren2");
		user.setPassword("anne3142");
		
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
		
		for (Role role : user.getRoles()) {
			log.debug("  (testUserFind) Roles for: " + user.getUserName() +" are " + role.getRole());
		}
		
		assertEquals(1, user.getRoles().size());
		assertEquals("Halgren2", user.getLastName());
		assertEquals("Anne2", user.getFirstName());
	}
	
	public void testUserDelete() {
		User user = new User();
		
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		user = userDAO.findUserByUsername("halgrena");
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
		
		for (User user : userList) {
			log.debug("Last Name (Find All): " + user.getLastName());
			for (Role role : user.getRoles()) {
				log.debug("   (testUserfindAll) Roles List 1: " + role.getRole());
			}
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
	}
}
