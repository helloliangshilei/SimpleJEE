package com.wickedhobo.DAO.hibernateDAO;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wickedhobo.config.ConfigurationInit;

import com.wickedhobo.DAO.simpleDAO.UserDAOImplSimple;

public class HibUtil {
	
	private static String hibernateConfigFile = ConfigurationInit.getHibernateConfigFile();
	private static String NOCONTEXTCONFIG = "hibernateAnnotations.cfg.xml";
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;
	
	private static Logger log = LoggerFactory.getLogger(UserDAOImplSimple.class);

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		try {
			if (hibernateConfigFile != null && !hibernateConfigFile.isEmpty() && hibernateConfigFile.equals("hibernateAnnotations.cfg.xml")) {
				configuration = new Configuration();
				configuration.configure(hibernateConfigFile);
			}
			else if (NOCONTEXTCONFIG != null && hibernateConfigFile == null) {
				configuration = new Configuration();
				configuration.configure(NOCONTEXTCONFIG);
			}
			else {
				configuration = new Configuration();
				configuration.configure();
			}
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			return configuration.buildSessionFactory(serviceRegistry);
		} 
		catch (ExceptionInInitializerError eiie) {
			log.error("Initial SessionFactory creation failed." + eiie);
			throw new ExceptionInInitializerError(eiie);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static SessionFactory getSessionFactory() {
			return sessionFactory;
	}
	
	public static Session beginTx() {
		Session hibernateSession = HibUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTx() {
		HibUtil.getSession().getTransaction().commit();
	}

	public static void rollBackTx() {
		HibUtil.getSession().getTransaction().rollback();
	}

	public static void closeSession() {
		HibUtil.getSession().close();
	}

	public static Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
}