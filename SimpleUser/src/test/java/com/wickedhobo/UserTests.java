package com.wickedhobo;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

import com.wickedhobo.DAO.hibernateDAO.HibUtil;
import com.wickedhobo.DAO.hibernateDAO.UserDAOHibSpringImpl;
import com.wickedhobo.object.Role;
import com.wickedhobo.object.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserTests {
	
	private static Logger log = LoggerFactory.getLogger(UserTests.class);
	
	@Autowired
	UserDAOHibSpringImpl userDAO;
	
	@Test
	@Transactional()
	public void testInsertUser() {
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
		log.debug("testInsertUser has passed all tests!");
	}

	
	@Test
	@Transactional()
	public void testFindUser() {
		
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
		log.debug("testFindUser has passed all tests.");
	}
}
