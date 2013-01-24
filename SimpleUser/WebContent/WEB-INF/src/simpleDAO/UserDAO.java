package simpleDAO;

import java.util.List;

import object.User;

public interface UserDAO {
	
	public void saveUser(User user);
	public void changeUser(User user);
	public User getUser(String username) throws Exception;
	public void removeUser(User user);
	public List<User> listAllUsers();

}
