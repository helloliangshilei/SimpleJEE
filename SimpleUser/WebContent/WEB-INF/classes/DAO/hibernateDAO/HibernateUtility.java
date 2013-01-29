package DAO.hibernateDAO;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.simpleDAO.UserDAOImplSimple;

public class HibernateUtility {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	private static Logger log = LoggerFactory.getLogger(UserDAOImplSimple.class);

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} 
		catch (ExceptionInInitializerError eiie) {
			log.error("Initial SessionFactory creation failed." + eiie);
			throw new ExceptionInInitializerError(eiie);
		}
	}

	public static SessionFactory getSessionFactory() {
			return sessionFactory;
	}

	public static Session beginTx() {
		Session hibernateSession = HibernateUtility.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTx() {
		HibernateUtility.getSession().getTransaction().commit();
	}

	public static void rollBackTx() {
		HibernateUtility.getSession().getTransaction().rollback();
	}

	public static void closeSession() {
		HibernateUtility.getSession().close();
	}

	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
}