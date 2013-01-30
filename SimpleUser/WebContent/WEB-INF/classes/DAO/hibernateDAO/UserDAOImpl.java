package DAO.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.simpleDAO.UserDAOImplSimple;

import object.User;

public class UserDAOImpl extends CommonDAOImpl<User> implements UserDAO {

	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	public void saveUser(User user) {
		try {
			HibernateUtility.beginTx();
			this.save(user);
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.saveUser: " + e);
			HibernateUtility.rollBackTx();
		}
	}

	public void updateUser(User user) {
		try {
			HibernateUtility.beginTx();
			this.update(user);
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.updateUser: " + e);
			HibernateUtility.rollBackTx();
		}
	}

	public User findUserByUsername(String username) {
		User user = null;
		try {
			HibernateUtility.beginTx();
			user = (User) this.find(User.class, username);
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.findUserByUsername: " + e);
			HibernateUtility.rollBackTx();
		}
		return user;
	}

	public void deleteUser(User user) {
		try {
			HibernateUtility.beginTx();
			this.delete(user);
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.deleteUser: " + e);
			HibernateUtility.rollBackTx();
		}
	}

	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		try {
			HibernateUtility.beginTx();
			users = this.findAll(User.class);
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.listUsers: " + e);
			HibernateUtility.rollBackTx();
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<User> listUsersByRoles(String role) {
		List<User> users = new ArrayList<User>();
		try {
			String sql = "select new User(user.userName, user.firstName, user.lastName, user.password) "
								 + "from User as user inner join user.roles role where role.role = :role";
			HibernateUtility.beginTx();
			Query query = HibernateUtility.getSession().createQuery(sql);
			query.setParameter("role", role);
			users = (List<User>) query.list();
			HibernateUtility.commitTx();
		}
		catch (Exception e) {
			log.error("Exception happened in DAO.hibernateDAO.UserDAOImpl.listUsersByRoles: " + e);
			HibernateUtility.rollBackTx();
		}
		return users;
	}
}
