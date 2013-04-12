package com.wickedhobo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class UserTestsHibernate {
	
	private static Logger log = LoggerFactory.getLogger(UserTests.class);
	
	@Test
	public void testUserSave() {
		User user = new User();
		
		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		
		UserDAO userDAO = new UserDAOHibOnlyImpl(); 
		userDAO.saveUser(user);
		
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
}
