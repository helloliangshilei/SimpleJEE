package DAO.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import object.User;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
public class UserDAOHibSpringImpl extends CommonDAOSpringImpl<User> implements UserDAO {

	@Transactional(readOnly = false)
	public void saveUser(User user) {
		this.save(user);
	}

	@Transactional(readOnly = false)
	public void updateUser(User user) {
		this.update(user);
	}

	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		User user = (User) this.find(User.class, username);
		Hibernate.initialize(user.getRoles());
		return user;
	}

	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		this.delete(user);
	}

	@Transactional(readOnly = true)
	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		users = this.findAll(User.class);
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> listUsersByRoles(String role) {
		List<User> users = new ArrayList<User>();

		String sql = "from User as user inner join fetch user.roles as role where role.role = :role";
		Query query = super.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("role", role);
		users = (List<User>) query.list();
		return users;
	}
}
