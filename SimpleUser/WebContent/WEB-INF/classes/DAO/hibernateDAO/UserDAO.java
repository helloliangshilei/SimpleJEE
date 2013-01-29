package DAO.hibernateDAO;

import java.util.List;

import object.User;

public interface UserDAO extends CommonDAO<User> {
	//Adding this as a good example of inheritance with added functionality
	public void saveUser(User user);
	public void updateUser (User user);
	public User findUserByUsername(String username);
	public void deleteUser(User user);
	public List<User> listUsers();
	public List<User> listUsersByRoles(String role);
}