package DAO.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import object.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDAOHibSpringImpl  {

	@Autowired
	private SessionFactory sessionFactory;
  
  public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
  }
  
  @Transactional(readOnly=false)
	public void saveUser(User user) {
			 sessionFactory.getCurrentSession().save(user);
	}

	/*public void updateUser(User user) {
		//hibernateTemplate.update(user);
	}*/

	/*public User findUserByUsername(String username) {
		User user = (User) this.find(User.class, username);
		return user;
	}*/

	/*public void deleteUser(User user) {
		//hibernateTemplate.delete(user);
	}*/

	/*@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		//Session session = sessionFactory.openSession();
		//List<User> users = session.get("from User");
		List<User> users = new ArrayList<User>();
		return users;
	}*/

	/*@SuppressWarnings("unchecked")
	public List<User> listUsersByRoles(String role) {
		List<User> users = new ArrayList<User>();
		//users = (List<User>) hibernateTemplate.find("select new User(user.userName, user.firstName, user.lastName, user.password) "
		//		+ "from User as user inner join user.roles role where role.role = :role", role);
		return users;
	}*/
}
