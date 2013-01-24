package simpleDAO;

import java.util.List;

import object.User;

public interface UserDAO {
	
	public int saveUser(User user) throws Exception;
	public int changeUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
	public int removeUser(User user) throws Exception;
	public List<User> listAllUsers() throws Exception;

}
