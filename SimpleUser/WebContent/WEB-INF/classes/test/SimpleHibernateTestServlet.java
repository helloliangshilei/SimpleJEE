package test;

import object.User;

import org.apache.cactus.ServletTestCase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SimpleHibernateTestServlet extends ServletTestCase {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	//TODO move this to a class for general use
	private static SessionFactory configureSessionFactory() {
    try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
	
	public SimpleHibernateTestServlet(String theName) {
		super(theName);
	}

	public static Test suite() {
		return new TestSuite(SimpleHibernateTestServlet.class);
	}
	
	public void setUp() {
		configureSessionFactory();
	}
	
	//TODO cleanup and add exception handling
	public void testUserGet() {
		User user = new User();
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		
		user.setFirstName("Anne");
		user.setLastName("Halgren");
		user.setUserName("halgrena");
		user.setPassword("anne314");
		
		session.save(user);
		tx.commit();
		session.close();
	}

}
